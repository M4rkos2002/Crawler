package sirius.challenge.crawler.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import sirius.challenge.crawler.model.URL
import java.util.Optional

interface UrlRepository : JpaRepository<URL, Long> {
    @Query("SELECT u FROM URL u WHERE u.content = :url")
    fun getByContent(@Param("url") url: String?): Optional<URL>

    fun existsByContent(content: String): Boolean
}
