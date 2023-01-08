package com.ikjunweb.service;

import com.ikjunweb.entity.board.Board;
import com.ikjunweb.entity.user.User;
import com.ikjunweb.repository.BoardRepository;
import com.ikjunweb.repository.UserRepository;
import com.ikjunweb.requestdto.board.BoardWriteRequest;
import com.ikjunweb.responsedto.board.BoardWriteResponse;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public BoardWriteResponse write(BoardWriteRequest boardWriteRequest) {
        String username = boardWriteRequest.getUsername();
        String email = boardWriteRequest.getEmail();
        User user = userRepository.findByUsernameAndEmail(username, email);

        if(user == null) {
            BoardWriteResponse boardWriteResponse = BoardWriteResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
            return boardWriteResponse;
        }
        if(!validateBoardWrite(boardWriteRequest)) {
            BoardWriteResponse boardWriteResponse = BoardWriteResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
            return boardWriteResponse;
        }

        String content = markdownToHtml(boardWriteRequest.getContent());

        Board board = Board.builder()
                .title(boardWriteRequest.getTitle())
                .content(content)
                .answer(boardWriteRequest.getAnswer())
                .explanation(boardWriteRequest.getExplanation())
                .major(boardWriteRequest.getMajor())
                .subject(boardWriteRequest.getSubject())
                .unlockStar(boardWriteRequest.getUnlockStar())
                .user(user)
                .build();
        boardRepository.save(board);

        BoardWriteResponse boardWriteResponse = BoardWriteResponse.builder()
                .nickname(user.getNickname())
                .title(board.getTitle())
                .subject(board.getSubject())
                .httpStatus(HttpStatus.OK)
                .build();
        return boardWriteResponse;
    }

    @Transactional
    public Board findBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("없는 글");
        });
        return board;
    }

    @Transactional
    public Page<Board> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    private boolean validateBoardWrite(BoardWriteRequest boardWriteRequest) {
        if(isNullAndEmpty(boardWriteRequest.getContent()) || isNullAndEmpty(boardWriteRequest.getAnswer()) || isNullAndEmpty(boardWriteRequest.getSubject())) {
            return false;
        }
        return true;
    }

    private boolean isNullAndEmpty(String str) {
        if(str == " " || str == "" || str == null) {
            return true;
        }
        return false;
    }

    private String markdownToHtml(String content) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(content);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }
}
