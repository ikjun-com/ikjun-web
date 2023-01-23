package com.ikjunweb.service;

import com.ikjunweb.entity.board.Board;
import com.ikjunweb.requestdto.board.BoardLikeRequest;
import com.ikjunweb.responsedto.board.BoardLikeResponse;

public interface BoardLikeService {
    Boolean pushLikeButton(BoardLikeRequest boardLikeRequest);
    Board getBoard(BoardLikeRequest boardLikeRequest);
    BoardLikeResponse getPostLikeInfo(BoardLikeRequest boardLikeRequest);
    boolean checkPushedLike(BoardLikeRequest boardLikeRequest);
    long getPostLikeNum(Long boardId);
}
