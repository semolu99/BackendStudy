package study.study.board.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import study.study.board.dto.BoardDtoRequest
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
            ?: throw InvalidInputException("User with id $userId does not exist")

        val board = boardDtoRequest.toEntity(member.name)
        boardRepository.save(board)
        return "게시글 작성 완료"
    }
}