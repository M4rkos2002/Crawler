package sirius.challenge.crawler.model

import jakarta.persistence.*

@Entity
class Frequency(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0,
    @Column var freq: Int = 0,
    @ManyToOne(fetch = FetchType.EAGER) var url: URL,
    @OneToOne(cascade = [(CascadeType.ALL)], fetch = FetchType.EAGER) var word: Word,
)