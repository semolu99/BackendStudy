package study.study.board.controller

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import study.study.board.dto.BoardDtoRequest
import study.study.board.service.BoardService
import study.study.common.dto.BaseResponse

@RestController
@RequestMapping("/api/board")
class BoardController (
    private val boardService: BoardService
) {
    /**
     * 게시글 작성
     */
    @PostMapping("/")
    fun boardPost(@RequestBody @Valid boardDtoRequest: BoardDtoRequest): BaseResponse<String>{
        val result = boardService.boardPost(boardDtoRequest)
        return BaseResponse(result)
    }
}