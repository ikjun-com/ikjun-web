package com.ikjunweb.repository.board_tag;

import com.ikjunweb.entity.board.BoardTag;

import java.util.List;

public interface BoardTagCustomRepository {
    List<BoardTag> findBoardTag(Long boardId);
}
