package study.study.member.service

import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder
import org.springframework.stereotype.Service
import study.study.common.authority.JwtTokenProvider
import study.study.common.authority.TokenInfo
import study.study.common.exception.InvalidInputException
import study.study.common.status.ROLE
import study.study.member.dto.LoginDto
import study.study.member.dto.MemberDtoRequest
import study.study.member.dto.MemberDtoResponse
import study.study.member.entity.Member
import study.study.member.entity.MemberRole
import study.study.member.repository.MemberRepository
import study.study.member.repository.MemberRoleRepository

@Transactional
@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberRoleRepository: MemberRoleRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider,
) {
    /**
     * 회원가입
     */
    fun signUp(memberDtoRequest: MemberDtoRequest): String {
        var member: Member? = memberRepository.findByLoginId(memberDtoRequest.loginId)
        if(member != null)
            return "이미 등록된 ID입니다."

        member = memberDtoRequest.toEntity()
        memberRepository.save(member)

        val memberRole = MemberRole(null, ROLE.MEMBER, member)
        memberRoleRepository.save(memberRole)

        return "회원가입이 완료되었습니다."
    }
    /**
     * 로그인
     */
    fun login(loginDto: LoginDto): TokenInfo {
        val member = memberRepository.findByLoginId(loginDto.loginId) ?: throw InvalidInputException("로그인 아이디 혹은 비밀번호가 틀렸습니다.")
        val encoder = SCryptPasswordEncoder(16,8,1,8,8)
        if(!encoder.matches(loginDto.password, member.password)){
            throw InvalidInputException("로그인 아이디 혹은 비밀번호가 틀렸습니다.")
        }
        val authenticationToken = UsernamePasswordAuthenticationToken(loginDto.loginId, member.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)
        return jwtTokenProvider.createToken(authentication)
    }
    /**
     * 내정보 조회
     */
    fun searchMyInfo(id: Long): MemberDtoResponse {
        val member = memberRepository.findByIdOrNull(id)
            ?:throw InvalidInputException("id", "회원번호가 존재하지 않는 유저입니다.")
        return member.toDto()
    }
    /**
     * 내 정보 수정
     */
    fun saveMyInfo(memberDtoRequest: MemberDtoRequest): String {
        val member = memberDtoRequest.toEntity()
        memberRepository.save(member)
        return "수정 완료."
    }
    /**
     * 같은 기숙사 타입 리스트
     */
    fun dormInfo(id: Long): List<MemberDtoResponse> {
        val dormType = memberRepository.findByIdOrNull(id)!!.dormType
        val result = memberRepository.findAllByDormType(dormType)
        return result.map { it.toDto() }
    }
}