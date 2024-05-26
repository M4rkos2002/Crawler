package sirius.challenge.crawler.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CrawlerService(
    @Autowired val frequencyService: FrequencyService,
    @Autowired val urlService: UrlService,
    @Autowired val wordService: WordService,
) {

}