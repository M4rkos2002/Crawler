package sirius.challenge.crawler.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import sirius.challenge.crawler.model.Frequency
import sirius.challenge.crawler.model.URL
import sirius.challenge.crawler.model.Word
import sirius.challenge.crawler.model.dto.FrequencyCreate
import sirius.challenge.crawler.repository.FrequencyRepository
import java.io.File

@Service
class FrequencyService(
    @Autowired val frequencyRepository: FrequencyRepository,
    @Value("\${word.cloud.generationfile}") val cloudGenerationFile: String,
) {

    fun createFrequency(body: FrequencyCreate): Frequency {
        val freq = Frequency()
        freq.url = body.url
        freq.word = body.word
        freq.freq = 0
        return frequencyRepository.save(freq)
    }

    fun existsByUrlAndWord(word: Word, url: URL): Boolean {
        return frequencyRepository.existsByUrlAndWord(url, word)
    }

    fun addOne(word: Word, url: URL): Frequency? {
        return if (frequencyRepository.existsByUrlAndWord(url, word)) {
            val frequency = frequencyRepository.getByUrlAndWord(url, word).orElseThrow()
            frequency.freq += 1
            frequencyRepository.save(frequency)
        } else {
            null
        }
    }

    fun generateFrequencyFile() {
        val frequencies = frequencyRepository.findAll()
        val sortedFrequencies = frequencies.sortedByDescending { it.freq }
        val outputFile = File(cloudGenerationFile)
        outputFile.bufferedWriter().use { writer ->
            sortedFrequencies.forEach { frequency ->
                writer.write("${frequency.freq}: ${frequency.word?.content}\n")
            }
        }
    }
}
