package com.ikjunweb.service;

import com.ikjunweb.entity.board.BoardTag;

import java.util.List;

public interface BoardTagService {
    List<BoardTag> getBoardByBoardTag();

    //board에 태그도 함께저장
    //boardTag로 board찾는 쿼리 기능
    //
}
