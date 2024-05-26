package sirius.challenge.crawler.crawler

interface Crawler {
    fun visit(url: String): String?
}