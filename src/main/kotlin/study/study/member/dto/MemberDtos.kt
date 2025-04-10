package study.study.member.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import study.study.common.annotation.ValidEnum
import study.study.common.status.DormType
import study.study.member.entity.Member
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class MemberDtoRequest(
    val id : Long?,

    @field:NotBlank
    @JsonProperty("loginId")
    private val _loginId : String?,

    @field:NotBlank
    @field:Pattern(
        regexp="^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*])[a-zA-Z0-9!@#\$%^&*]{8,20}\$",
        message = " 영문, 숫자, 특수문자를 포함한 8~20 자리로 입력해주세요"
    )
    @JsonProperty("password")
    private val _password : String?,

    @field:NotBlank
    @JsonProperty("name")
    private val _name : String?,

    @field:NotBlank
    @field:Email
    @JsonProperty("email")
    private val _email : String?,

    @field:NotBlank
    @field:ValidEnum(enumClass = DormType::class,
        message = "기숙사 값을 확인해주세요")
    @JsonProperty("dormType")
    private val _dormType: String?,
){
    val loginId: String
        get() = _loginId!!

    val password: String
        get() = _password!!

    val name: String
        get() = _name!!

    val email: String
        get() = _email!!

    val dormType: DormType
        get() = DormType.valueOf(_dormType!!)

    fun toEntity(): Member =
        Member(id, loginId, password, name, email, dormType)
}

data class LoginDto(
    @field:NotBlank
    @JsonProperty("loginId")
    private val _loginId : String?,

    @field:NotBlank
    @JsonProperty("password")
    private val _password : String?,
) {
    val loginId: String
        get() = _loginId!!
    val password: String
        get() = _password!!
}