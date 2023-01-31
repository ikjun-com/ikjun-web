package com.ikjunweb.requestdto.comment;

import com.ikjunweb.entity.board.Board;
import com.ikjunweb.entity.comment.Comment;
import com.ikjunweb.entity.user.User;
import lombok.Data;

@Data
public class CommentWriteRequest {
    Long userId;
    Long boardId;
    String content;

    public Comment createComment(User user, Board board) {
        return Comment.builder()
                .user(user)
                .board(board)
                .content(content)
                .build();
    }
}
