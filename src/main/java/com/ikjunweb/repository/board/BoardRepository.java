package com.ikjunweb.repository.board;

import com.ikjunweb.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardCustomRepository{
}
