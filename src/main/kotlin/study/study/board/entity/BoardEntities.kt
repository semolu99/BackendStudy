package study.study.board.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Board(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(nullable = false, length = 30, updatable = true)
    var title: String,
    @Column(nullable = false, length = 500, updatable = true)
    var content: String,
    @Column(nullable = false, length = 10, updatable = false)
    val writer : String,
    @Column(nullable = false, updatable = true)
    var likes : Long,
    @Column(nullable = false, length = 30, updatable = false)
    val createDate: LocalDateTime
) {
}