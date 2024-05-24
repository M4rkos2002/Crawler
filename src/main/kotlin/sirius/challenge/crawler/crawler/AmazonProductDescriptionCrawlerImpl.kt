package sirius.challenge.crawler.crawler

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class AmazonProductDescriptionCrawlerImpl : Crawler {
    override fun visit(url: String): String? {
        val userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36"
        return try {
            val doc: Document = Jsoup.connect(url)
                .userAgent(userAgent) // Configurar el agente de usuario
                .get()

            val description: Element? = doc.getElementById("productDescription")
            println(description?.text().toString())
            val paragraph = description?.select("p")?.first()
            val result = paragraph?.text()
            return result
        } catch (e: NullPointerException) {
            println(e.message)
            null
        }
    }
}

/*
    Document is a Library which has methods to make querrys easily
 */
