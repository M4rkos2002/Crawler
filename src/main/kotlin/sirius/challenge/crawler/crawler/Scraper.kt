package sirius.challenge.crawler.crawler

interface Scraper {
    fun visit(url: String): String?
}