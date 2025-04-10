package study.study.member.controller

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import study.study.common.authority.TokenInfo
import study.study.common.dto.BaseResponse
import study.study.member.dto.LoginDto
import study.study.member.dto.MemberDtoRequest
import study.study.member.service.MemberService

@RequestMapping("/api/member")
@RestController
class MemberController(
    private val memberService: MemberService
) {
    /**
     * titleId == (50)
     */
    @GetMapping("/{titleId}")
    fun test(@PathVariable("titleId") titleId: Int) : String {
        if (titleId == 50){
            return "50입니다."
        }
        return "hello world"
    }
    /**
     * 회원가입
     */
    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid memberDtoRequest: MemberDtoRequest): BaseResponse<Unit> {
        val resultMsg = memberService.signUp(memberDtoRequest)
        return BaseResponse(message = resultMsg)
    }
    /**
     * 로그인
     */
    @PostMapping("/login")
    fun login(@RequestBody @Valid loginDto: LoginDto): BaseResponse<TokenInfo> {
        val tokenInfo = memberService.login(loginDto)
        return BaseResponse(data = tokenInfo)
    }
}