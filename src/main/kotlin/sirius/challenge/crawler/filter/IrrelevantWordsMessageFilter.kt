package sirius.challenge.crawler.filter

import org.springframework.core.io.ClassPathResource
import java.io.BufferedReader
import java.io.InputStreamReader


class IrrelevantWordsMessageFilter(
    private val path: String = "stopwords/english"
) : MessageFilter {

    override fun filter(message: String): String {
        val stopWords = this.loadStopWords()
        val filteredMessage = filterWords(message, stopWords)
        var result = ""
        filteredMessage.forEach {
            word -> result = "$result $word"
        }
        return result
    }

    /*
        Load stopwords file
     */
    private fun loadStopWords(): Set<String> {
        val stopWords = mutableSetOf<String>()
        val resource = ClassPathResource(path)
        BufferedReader(InputStreamReader(resource.inputStream)).use { reader ->
            reader.forEachLine { stopWords.add(it.trim().toLowerCase()) }
        }
        return stopWords
    }

    /*
        Filters message in words
     */
    private fun filterWords(text: String, stopwords: Set<String>): List<String> {
        val words = text.split("\\s+".toRegex())
        return words.filter { it.toLowerCase() !in stopwords }
    }

}