package sirius.challenge.crawler.service

import org.openqa.selenium.bidi.network.ResponseData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CrawlerService(
    @Autowired val frequencyService: FrequencyService,
    @Autowired val urlService: UrlService,
    @Autowired val wordService: WordService,
) {

    fun persistData(url: String): ResponseData {
        return when {
            urlService.urlExists(url) -> persistKnownURL(url)
            else -> persistNonKnownURL(url)
        }
    }

    private fun persistKnownURL(url: String): ResponseData {
        TODO()
    }

    private fun persistNonKnownURL(url: String): ResponseData {
        TODO()
    }
}