package study.study.study.member.controller

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import study.study.member.controller.MemberController
import study.study.member.dto.MemberDtoRequest
import study.study.member.service.MemberService

@WebMvcTest
@AutoConfigureMockMvc
class MemberControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {
    @MockitoBean
    private lateinit var memberService: MemberService

    @Test
    fun `should return success message when sign up is successful`() {
        val memberDto = MemberDtoRequest(
            null,
            _loginId = "test123",
            _password = "test1234",
            _name = "테스트",
            _birthDate = "1999-02-27",
            _gender = "MAN",
            _email = "test@test.com"
        )

        // 의존성 주입(mocking)
        val mockMemberService = mock(MemberService::class.java)
        `when`(mockMemberService.signUp(memberDto)).thenReturn("회원가입이 완료되었습니다.")

        val controller = MemberController(mockMemberService)
        val response = controller.signUp(memberDto)

        assertEquals("회원가입이 완료되었습니다.", response.message)
    }

}