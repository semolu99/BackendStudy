package study.study.board.service

import org.springframework.stereotype.Service
import study.study.board.dto.BoardDtoRequest
import study.study.board.repository.BoardRepository

@Service
class BoardService(
    private val boardRepository: BoardRepository,
) {
    /**
     * 게시글 작성
      */
    fun boardPost(boardDtoRequest: BoardDtoRequest) : String {
        val board = boardDtoRequest.toEntity()
        boardRepository.save(board)
        return "게시글 작성 완료"
    }
}