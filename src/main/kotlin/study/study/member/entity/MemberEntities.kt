package study.study.member.entity

import com.fasterxml.jackson.databind.annotation.EnumNaming
import jakarta.persistence.*
import study.study.common.status.DormType
import java.time.LocalDate

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(name = "uk_member_login_id", columnNames = ["loginId"])]
)
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null,

    @Column(nullable = false, length = 30, updatable = false)
    val loginId : String,

    @Column(nullable = false, length = 100)
    val password: String,

    @Column(nullable = false, length = 10)
    val name : String,

    @Column(nullable = false, length = 30)
    val email : String,

    @Column(nullable = false, length = 10)
    val dormType: DormType
)