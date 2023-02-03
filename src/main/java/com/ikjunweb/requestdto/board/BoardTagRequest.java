package com.ikjunweb.requestdto.board;

import com.ikjunweb.entity.board.Board;
import com.ikjunweb.entity.board.BoardTag;
import lombok.Builder;
import lombok.Data;

@Data
public class BoardTagRequest {
    private String name;
    private Long boardId;

    public BoardTag createBoardTag(String name, Board board) {
        return BoardTag.builder()
                .name(name)
                .board(board)
                .build();
    }
}
