package sirius.challenge.crawler.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import sirius.challenge.crawler.service.CrawlerService

@Controller
@RequestMapping("/scrap")
class ScrapController(
    @Autowired val crawlerService: CrawlerService
) {

    @PostMapping("")
    fun scrapData(@RequestParam("PARAM") url: String): ResponseEntity<Boolean> {
        val response = crawlerService.persistData(url)
        return response
    }
}
