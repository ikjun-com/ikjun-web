package com.ikjunweb.repository.board;

import com.ikjunweb.entity.board.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long>, BoardLikeCustomRepository {
    void deleteByBoardId(Long boardId);
}
