package com.ikjunweb.service;

import com.ikjunweb.entity.board.Board;
import com.ikjunweb.entity.board.BoardLike;
import com.ikjunweb.repository.BoardLikeRepository;
import com.ikjunweb.repository.BoardRepository;
import com.ikjunweb.requestdto.board.BoardLikeRequest;
import com.ikjunweb.responsedto.board.BoardLikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardLikeServiceImpl implements BoardLikeService{

    private final BoardLikeRepository boardLikeRepository;
    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public Boolean pushLikeButton(BoardLikeRequest boardLikeRequest) {
        Optional<BoardLike> OBoardLike = boardLikeRepository.exist(boardLikeRequest.getUser().getId(), boardLikeRequest.getBoardId());
        Board board = getBoardByLike(boardLikeRequest);
        if (OBoardLike.isPresent()) {
            BoardLike boardLike = OBoardLike.get();
            boardLikeRepository.deleteById(boardLikeRequest.getBoardId());
        } else {
            boardLikeRepository.save(new BoardLike(board, boardLikeRequest.getUser()));
        }
        board.setLikeCount(getPostLikeNum(board.getId()));
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Board getBoardByLike(BoardLikeRequest boardLikeRequest) {
        return boardRepository.findById(boardLikeRequest.getBoardId()).orElseThrow(() -> new IllegalArgumentException("해당 게시글은 존재하지 않습니다."));
    }

    @Override
    public BoardLikeResponse getPostLikeInfo(BoardLikeRequest boardLikeRequest) {
        return BoardLikeResponse.builder()
                .nickname(boardLikeRequest.getUser().getNickname())
                .title(getBoardByLike(boardLikeRequest).getTitle())
                .check(checkPushedLike(boardLikeRequest))
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public boolean checkPushedLike(BoardLikeRequest boardLikeRequest) {
        return boardLikeRepository.exist(boardLikeRequest.getUser().getId(), boardLikeRequest.getBoardId()).isPresent();
    }

    @Override
    public long getPostLikeNum(Long boardId) {
        return boardLikeRepository.findPostLikeNum(boardId);
    }
}
