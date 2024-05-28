package sirius.challenge.crawler.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import sirius.challenge.crawler.model.Word
import java.util.*

@Repository
interface WordRepository : JpaRepository<Word, Long> {
    @Query("SELECT w FROM Word w WHERE w.content = :value")
    fun getByContent(@Param("value") content: String): Optional<Word>

    @Query("SELECT CASE WHEN COUNT(w) = 1 THEN true ELSE false END FROM Word w WHERE w.content = :content")
    fun existsByContent(@Param("content") content: String?): Boolean
}