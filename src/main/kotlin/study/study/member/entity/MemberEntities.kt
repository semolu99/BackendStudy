package study.study.member.entity

import com.fasterxml.jackson.databind.annotation.EnumNaming
import jakarta.persistence.*
import study.study.common.status.DormType
import study.study.common.status.ROLE
import study.study.member.dto.MemberDtoResponse
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

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false, length = 10)
    val name : String,

    @Column(nullable = false, length = 30)
    val email : String,

    @Column(nullable = false, length = 10)
    val dormType: DormType
) {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    val memberRole : List<MemberRole>? = null

    fun toDto(): MemberDtoResponse =
        MemberDtoResponse(
            id!!,
            loginId,
            name,
            email,
            dormType.desc
        )
}

@Entity
class MemberRole(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null,

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    val role : ROLE,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = ForeignKey(name = "fk_user_role_member_id"))
    val member: Member,
)