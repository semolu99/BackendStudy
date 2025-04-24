package study.study.board.controller

import jakarta.validation.Valid
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import study.study.board.dto.BoardDtoRequest
import study.study.board.entity.Board
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
    @PostMapping("/")
    fun boardPost(@RequestBody @Valid boardDtoRequest: BoardDtoRequest): BaseResponse<Unit>{
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        val result = boardService.boardPost(boardDtoRequest, userId)
        return BaseResponse(result)
    }
    /**
     * 전체 게시글 가져오기
     */
    @GetMapping("/")
    fun allGetPosts() : BaseResponse<MutableList<Board>> {
        val list = boardService.allGetPosts()
        return BaseResponse(data = list)
    }
    /**
     * 특정 게시판 가져오기
     */
    @GetMapping("/{id}")
    fun getPosts(@PathVariable id : Long) : BaseResponse<Board> {
        val result = boardService.getPost(id)
        return BaseResponse(data = result)
    }
}