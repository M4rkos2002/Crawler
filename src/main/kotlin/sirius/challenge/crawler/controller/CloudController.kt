package sirius.challenge.crawler.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import sirius.challenge.crawler.service.FrequencyService
import sirius.challenge.crawler.wordColud.RectangleWordCloudGenerator

@Controller
@RequestMapping("/cloud")
class CloudController(
    @Autowired val frequencyService: FrequencyService,
    @Value("\${word.cloud.generationfile}") val cloudGenerationFile: String,
) {

    @PostMapping("")
    fun generateAll(): ResponseEntity<Boolean> {
        frequencyService.generateFrequencyFile()
        val generator = RectangleWordCloudGenerator()
        generator.generateImage(cloudGenerationFile)
        return ResponseEntity.ok(true)
    }
}