package sirius.challenge.crawler.filter

import org.springframework.core.io.ClassPathResource
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*


class IrrelevantWordsMessageFilter(
    private val path: String = "stopwords/english"
) : MessageFilter {

    override fun filter(message: String): String {
        val stopWords = this.loadStopWords()
        val filteredMessage = filterHelper(message, stopWords)
        return filteredMessage.joinToString(" ")
    }

    private fun filterHelper(text: String, stopWords: Set<String>): List<String> {
        val words = text.split("\\s+".toRegex())
        return words.filter { word ->
            val cleanWord = word.trim().replace(Regex("^[^a-zA-Z0-9\"]+|[^a-zA-Z0-9\"]+$"), "")
            cleanWord.isNotEmpty() && !stopWords.contains(cleanWord.toLowerCase())
        }.map { word ->
            word.trim().replace(Regex("[.,]+$"), "")
        }
    }

    /*
        Load stopwords file
     */
    private fun loadStopWords(): Set<String> {
        val stopWords = mutableSetOf<String>()
        val resource = ClassPathResource(path)
        BufferedReader(InputStreamReader(resource.inputStream)).use { reader ->
            reader.forEachLine { stopWords.add(it.trim().lowercase(Locale.getDefault())) }
        }
        return stopWords
    }
}
