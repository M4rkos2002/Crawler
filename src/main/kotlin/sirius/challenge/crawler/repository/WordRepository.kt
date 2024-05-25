package sirius.challenge.crawler.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import sirius.challenge.crawler.model.Word
import java.util.*

@Repository
interface WordRepository : JpaRepository<Word, Long> {
    @Query("SELECT w FROM Word w WHERE lower(w.content) = lower(:value)")
    fun getByContent(@Param("value") content: String): Optional<Word>

    fun existsByContent(content: String): Boolean
}