package study.study.board.controller

import jakarta.validation.Valid
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import study.study.board.dto.BoardDtoRequest
import study.study.board.service.BoardService
import study.study.common.dto.BaseResponse
import study.study.common.dto.CustomUser

@RestController
@RequestMapping("/api/board")
class BoardController (
    private val boardService: BoardService
) {
    /**
     * 게시글 작성
     */
    @PostMapping("/post")
    fun boardPost(@RequestBody @Valid boardDtoRequest: BoardDtoRequest): BaseResponse<Unit>{
        println("****************************************")
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        val result = boardService.boardPost(boardDtoRequest, userId)
        return BaseResponse(result)
    }
}