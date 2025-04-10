package study.study.board.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import study.study.board.entity.Board
import java.time.LocalDateTime

data class BoardDtoRequest(
    val id: Long? = null,

    @field:NotBlank
    @JsonProperty("title")
    private val _title : String?,

    @field:NotBlank
    @JsonProperty("content")
    private val _content : String?,

    private val writer : String = "익명의 작성자",
    private val likes : Long = 0,
    private val createDate: LocalDateTime = LocalDateTime.now()
) {
    val title: String
        get() = _title!!.toString()
    val content: String
        get() = _content!!.toString()


    fun toEntity() : Board = Board(
        null,title,content,writer,likes,createDate
    )
}