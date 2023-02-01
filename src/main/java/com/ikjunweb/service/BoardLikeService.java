package com.ikjunweb.service;

import com.ikjunweb.entity.board.Board;
import com.ikjunweb.entity.board.BoardLike;
import com.ikjunweb.requestdto.board.BoardLikeRequest;
import com.ikjunweb.responsedto.board.BoardLikeResponse;

import java.util.List;

public interface BoardLikeService {
    Boolean pushLikeButton(BoardLikeRequest boardLikeRequest);
    Board getBoardByLike(BoardLikeRequest boardLikeRequest);
    BoardLikeResponse getPostLikeInfo(BoardLikeRequest boardLikeRequest);
    boolean checkPushedLike(BoardLikeRequest boardLikeRequest);
    long getPostLikeNum(Long boardId);
    List<BoardLike> getUserLike(Long userId);
}
