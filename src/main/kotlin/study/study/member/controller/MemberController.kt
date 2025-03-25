package study.study.member.controller

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import study.study.common.dto.BaseResponse
import study.study.member.dto.MemberDtoRequest
import study.study.member.service.MemberService

@RequestMapping("/api/member")
@RestController
class MemberController(
    private val memberService: MemberService
) {
    /**
     * post확인
     */
    @PostMapping("/posts")
    fun post(@RequestParam params : Map<String, String>) : String{
        return "Hello World"
    }
    /**
     * 회원가입
     */
    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid memberDtoRequest: MemberDtoRequest): BaseResponse<Unit> {
        val resultMsg = memberService.signUp(memberDtoRequest)
        return BaseResponse(message = resultMsg)
    }
}