package sirius.challenge.crawler.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import sirius.challenge.crawler.crawler.AmazonProductDescriptionScraperImpl
import sirius.challenge.crawler.filter.IrrelevantWordsMessageFilter
import sirius.challenge.crawler.model.Word
import sirius.challenge.crawler.model.dto.FrequencyCreate
import sirius.challenge.crawler.model.dto.UrlCreate
import sirius.challenge.crawler.model.dto.WordCreate

@Service
class ScraperService(
    @Autowired val frequencyService: FrequencyService,
    @Autowired val urlService: UrlService,
    @Autowired val wordService: WordService,
) {

    fun persistData(url: String): ResponseEntity<Boolean> {
        return when {
            urlService.urlExists(url) -> persistKnownURL(url)
            else -> persistNonKnownURL(url)
        }
    }

    private fun persistKnownURL(url: String): ResponseEntity<Boolean> {
        return ResponseEntity.ok().body(true)
    }

    private fun persistNonKnownURL(url: String): ResponseEntity<Boolean> {
        val urlEntity = urlService.createUrl(UrlCreate(url))
        val scrapResult = executeScraper(url) ?: return ResponseEntity.badRequest().body(false)
        val filterResult = executeFilter(scrapResult)
        val words = getWordIntoList(filterResult)
        println(words)
        words.forEach {
            word ->
            val wordEntity: Word =
                when {
                    wordService.wordExistsByContent(word) -> wordService.getWordByContent(word)!!
                    else -> wordService.createWord(WordCreate(word))
                }
            when {
                frequencyService.existsByUrlAndWord(wordEntity, urlEntity) -> frequencyService.addOne(wordEntity, urlEntity)
                else -> {
                    frequencyService.createFrequency(FrequencyCreate(urlEntity, wordEntity))
                    frequencyService.addOne(wordEntity, urlEntity)
               }
            }
        }
        return ResponseEntity.ok().body(true)
    }

    private fun executeScraper(url: String): String? {
        val crawler = AmazonProductDescriptionScraperImpl()
        val result = crawler.visit(url)
        return result
    }

    private fun executeFilter(message: String): String {
        val filter = IrrelevantWordsMessageFilter()
        val result = filter.filter(message)
        return result
    }

    private fun getWordIntoList(text: String): List<String> {
        val cleanedText = text.replace(".", "")
        return cleanedText.split("\\s+".toRegex())
    }
}
