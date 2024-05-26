package sirius.challenge.crawler.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import sirius.challenge.crawler.model.Frequency
import sirius.challenge.crawler.model.dto.FrequencyCreate
import sirius.challenge.crawler.repository.FrequencyRepository

@Service
class FrequencyService(
    @Autowired val frequencyRepository: FrequencyRepository
) {

    fun createFrequency(body: FrequencyCreate): Frequency {
        val freq = Frequency()
        freq.url = body.url
        freq.word = body.word
        freq.freq = 0
        return frequencyRepository.save(freq)
    }

    fun addOne(word: String, url: String): Frequency? {
        if (frequencyRepository.existsByUrlAndWord(url, word)) {
            val frequency = frequencyRepository.getByUrlAndWord(url, word).get()
            frequency.freq += 1
            return frequencyRepository.save(frequency)
        }
        return null
    }
}