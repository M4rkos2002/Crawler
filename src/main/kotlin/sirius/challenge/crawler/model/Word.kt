package sirius.challenge.crawler.model

import jakarta.persistence.*

@Entity
class Word(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long? = null,
    @Column(nullable = false) var content: String = "",
    @Column(nullable = false) var frequency: Int = 0,
    @ManyToOne(fetch = FetchType.EAGER) var url: URL,
) {
}