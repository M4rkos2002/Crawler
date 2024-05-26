package sirius.challenge.crawler.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import sirius.challenge.crawler.model.Frequency
import java.util.*

@Repository
interface FrequencyRepository : JpaRepository<Frequency, Long> {
    @Query("SELECT f FROM Frequency f WHERE f.url.content = :url AND f.word.content = :word")
    fun existsByUrlAndWord(@Param("url") url: String, @Param("word") word: String): Boolean

    @Query("SELECT f FROM Frequency f WHERE f.url.content = :url AND f.word.content = :word")
    fun getByUrlAndWord(@Param("url") url: String,@Param("word") word: String): Optional<Frequency>
}
