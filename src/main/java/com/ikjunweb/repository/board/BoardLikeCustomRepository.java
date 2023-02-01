package com.ikjunweb.repository.board;

import com.ikjunweb.entity.board.Board;
import com.ikjunweb.entity.board.BoardLike;

import java.util.List;
import java.util.Optional;

public interface BoardLikeCustomRepository {
    Optional<BoardLike> exist(Long userId, Long boardId);
    long findPostLikeNum(Long boardId);
    List<BoardLike> findBoardByLike(Long userId);
}
