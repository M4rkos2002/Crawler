package sirius.challenge.crawler.model

import jakarta.persistence.*

@Entity
class URL(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0,
    @Column(nullable = false) var content: String = "",
    @OneToMany(mappedBy = "url") var frequencys: MutableSet<Frequency> = mutableSetOf(),
)
