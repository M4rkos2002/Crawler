package sirius.challenge.crawler.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import sirius.challenge.crawler.model.Word
import sirius.challenge.crawler.model.dto.WordCreate
import sirius.challenge.crawler.repository.WordRepository

@Service
class WordService(
    @Autowired val wordRepository: WordRepository
){

    fun createWord(body: WordCreate): Word {
        val word = Word()
        word.content = body.content
        word.frequencys = mutableListOf()
        return wordRepository.save(word)
    }

    fun wordExistsByContent(content: String): Boolean{
        return wordRepository.existsByContent(content)
    }

    fun getWordByContent(content: String): Word? {
        if (wordRepository.existsByContent(content)) {
            return wordRepository.getByContent(content).get()
        }
        return null
    }
}