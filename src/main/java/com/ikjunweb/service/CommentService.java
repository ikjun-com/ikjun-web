package com.ikjunweb.service;

import com.ikjunweb.entity.board.Board;
import com.ikjunweb.entity.comment.Comment;
import com.ikjunweb.entity.user.User;
import com.ikjunweb.repository.board.BoardRepository;
import com.ikjunweb.repository.comment.CommentRepository;
import com.ikjunweb.repository.user.UserRepository;
import com.ikjunweb.requestdto.comment.CommentWriteRequest;
import com.ikjunweb.responsedto.comment.CommentWriteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentWriteResponse writeComment(CommentWriteRequest commentWriteRequestDto) {
        User user = userRepository.findById(commentWriteRequestDto.getUserId()).orElseThrow(() -> {return new IllegalArgumentException("");});
        Board board = boardRepository.findById(commentWriteRequestDto.getBoardId()).orElseThrow(() -> {return new IllegalArgumentException("");});
        Comment comment = commentWriteRequestDto.createComment(user, board);

        commentRepository.save(comment);
        board.setCommentCount(commentRepository.findCommentNum(commentWriteRequestDto.getBoardId()));

        return CommentWriteResponse.builder()
                .nickname(user.getNickname())
                .title(board.getTitle())
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Transactional(readOnly = true)
    public long getCommentNum(Long boardId) {
        return commentRepository.findCommentNum(boardId);
    }

    @Transactional(readOnly = true)
    public List<Comment> getComments(Long boardId) {
        return commentRepository.findCommentInBoard(boardId);
    }
}
