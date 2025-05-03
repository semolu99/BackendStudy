package study.study.member.repository

import org.springframework.data.jpa.repository.JpaRepository
import study.study.common.status.DormType
import study.study.member.dto.MemberDtoResponse
import study.study.member.entity.Member
import study.study.member.entity.MemberRole

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByLoginId(loginId: String) : Member?
    fun findAllByDormType(dormType: DormType) : MutableList<Member>
}

interface MemberRoleRepository : JpaRepository<MemberRole, Long>