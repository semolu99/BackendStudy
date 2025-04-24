package study.study.board.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import study.study.board.dto.BoardDtoRequest
import study.study.board.entity.Board
import study.study.board.repository.BoardRepository
import study.study.common.exception.InvalidInputException
import study.study.member.repository.MemberRepository

@Service
class BoardService(
    private val boardRepository: BoardRepository,
    private val memberRepository: MemberRepository
) {
    /**
     * 게시글 작성
      */
    fun boardPost(boardDtoRequest: BoardDtoRequest, userId: Long) : String {
        val member = memberRepository.findByIdOrNull(userId)
            ?: throw InvalidInputException("해당하는 유저가 없습니다.")

        val board = boardDtoRequest.toEntity(member.name)
        boardRepository.save(board)
        return "게시글 작성 완료"
    }
    /**
     * 전체 게시글 가져오기
     */
    fun allGetPosts() : MutableList<Board> {
        return boardRepository.findAll()
    }
    /**
     * 특정 게시판 가져오기
     */
    fun getPost(postId: Long) : Board {
        val board = boardRepository.findBoardById(postId) ?: throw InvalidInputException("게시글 번호 : $postId 존재하지 않는 게시글 입니다.")
        return board
    }

}