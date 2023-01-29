package com.ikjunweb.repository;

import com.ikjunweb.entity.SearchCondition;
import com.ikjunweb.entity.board.Board;

import java.util.List;

public interface BoardCustomRepository {
    List<Board> search(SearchCondition searchCondition);
}
