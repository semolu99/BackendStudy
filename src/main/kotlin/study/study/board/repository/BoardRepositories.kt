package study.study.board.repository

import org.springframework.data.jpa.repository.JpaRepository
import study.study.board.entity.Board

interface BoardRepository : JpaRepository<Board, Long>{
    fun findBoardById(boardId: Long): Board?
}