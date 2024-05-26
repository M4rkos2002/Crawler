package sirius.challenge.crawler.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import sirius.challenge.crawler.model.Word
import sirius.challenge.crawler.model.dto.WordCreate
import sirius.challenge.crawler.service.WordService

@Controller
@RequestMapping("/word")
class WordController(
    @Autowired val wordService: WordService
) {


    @PostMapping("")
    fun word(@RequestBody body: WordCreate): ResponseEntity<Word> {
        val word = wordService.createWord(body)
        return ResponseEntity.ok(word)
    }

    @GetMapping("")
    fun getByContent(@RequestParam("word") content: String): ResponseEntity<Word> {
        val word = wordService.getWordByContent(content)
        println(word)
        return ResponseEntity(word, HttpStatus.OK)
    }
}