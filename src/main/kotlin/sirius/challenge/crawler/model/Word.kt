package sirius.challenge.crawler.model

import jakarta.persistence.*

@Entity
class Word(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0,
    @Column(nullable = false) var content: String = "",
    @OneToOne(mappedBy = "word", fetch = FetchType.EAGER)  var frequency: Frequency? = null,
)
