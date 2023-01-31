package com.ikjunweb.repository.comment;

import com.ikjunweb.entity.comment.Comment;

import java.util.List;

public interface CommentCustomRepository {
    List<Comment> findCommentInBoard(Long boardId);
    long findCommentNum(Long boardId);
}
