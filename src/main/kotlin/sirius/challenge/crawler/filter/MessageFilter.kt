package sirius.challenge.crawler.filter

interface MessageFilter {
    fun filter(message: String): String
}