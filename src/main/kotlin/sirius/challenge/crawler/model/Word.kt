package sirius.challenge.crawler.model

import jakarta.persistence.*

@Entity
open class Word(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0,
    @Column(nullable = false) var content: String = "",
    @OneToMany(mappedBy = "word", fetch = FetchType.EAGER)  var frequencys: MutableList<Frequency> = mutableListOf(),
)
