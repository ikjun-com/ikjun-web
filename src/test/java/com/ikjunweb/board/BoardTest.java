package com.ikjunweb.board;

import com.ikjunweb.entity.board.Board;
import com.ikjunweb.repository.board.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

public class BoardTest {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardTest(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Test
    void boardFind() {
        Board board = boardRepository.findById(1L).orElseThrow(() -> {
            return new IllegalArgumentException("없는 글");
        });
        assertThat(board).isEqualTo(null);
    }

    @Test
    void 유저가_쓴_글_목록() {

    }
}
