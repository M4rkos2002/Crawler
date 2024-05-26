package sirius.challenge.crawler.model.dto

import sirius.challenge.crawler.model.URL
import sirius.challenge.crawler.model.Word

data class WordCreate(val content: String = "")

data class UrlCreate(val url: String = "")

data class FrequencyCreate(val url: URL? = null, val word: Word? = null)
