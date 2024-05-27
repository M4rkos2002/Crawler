package sirius.challenge.crawler.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import sirius.challenge.crawler.model.Frequency
import sirius.challenge.crawler.model.URL
import sirius.challenge.crawler.model.Word
import java.util.*

@Repository
interface FrequencyRepository : JpaRepository<Frequency, Long> {

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN TRUE ELSE FALSE END FROM Frequency f WHERE f.url = :url AND f.word = :word")
    fun existsByUrlAndWord(@Param("url") url: URL, @Param("word") word: Word): Boolean

    @Query("SELECT f FROM Frequency f WHERE f.url = :url AND f.word = :word")
    fun getByUrlAndWord(@Param("url") url: URL, @Param("word") word: Word): Optional<Frequency>
}