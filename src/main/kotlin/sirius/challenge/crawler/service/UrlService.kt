package sirius.challenge.crawler.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import sirius.challenge.crawler.model.URL
import sirius.challenge.crawler.model.dto.UrlCreate
import sirius.challenge.crawler.repository.UrlRepository

@Service
class UrlService(
    @Autowired val urlRepository: UrlRepository
) {

    fun createUrl(body: UrlCreate): URL {
        val url = URL()
        url.content = body.url
        url.frequencys = mutableListOf()
        return urlRepository.save(url)
    }

    fun urlExists(urlName: String): Boolean {
        return urlRepository.existsByContent(urlName)
    }

    fun getUrlByContent(content: String): URL? {
        return when {
            urlRepository.existsByContent(content) -> urlRepository.getByContent(content).get()
            else -> null
        }
    }
}