package com.ikjunweb.service;

import com.ikjunweb.entity.SearchCondition;
import com.ikjunweb.entity.board.Board;
import com.ikjunweb.entity.board.BoardHate;
import com.ikjunweb.entity.board.BoardLike;
import com.ikjunweb.entity.user.User;
import com.ikjunweb.repository.BoardHateRepository;
import com.ikjunweb.repository.BoardLikeRepository;
import com.ikjunweb.repository.BoardRepository;
import com.ikjunweb.repository.UserRepository;
import com.ikjunweb.requestdto.board.BoardEditRequest;
import com.ikjunweb.requestdto.board.BoardWriteRequest;
import com.ikjunweb.responsedto.board.BoardEditResponse;
import com.ikjunweb.responsedto.board.BoardHateResponse;
import com.ikjunweb.responsedto.board.BoardLikeResponse;
import com.ikjunweb.responsedto.board.BoardWriteResponse;
import lombok.extern.slf4j.Slf4j;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final BoardHateRepository boardHateRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, UserRepository userRepository, BoardLikeRepository boardLikeRepository, BoardHateRepository boardHateRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.boardLikeRepository = boardLikeRepository;
        this.boardHateRepository = boardHateRepository;
    }

    @Transactional
    public List<Board> searchBoard(SearchCondition searchCondition) {
        return boardRepository.search(searchCondition);
    }

    @Transactional
    public List<Board> findAll() {
        return boardRepository.findAll();
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
                .majorType(boardWriteRequest.getMajorType())
                .subjectType(boardWriteRequest.getSubjectType())
                .unlockStar(boardWriteRequest.getUnlockStar())
                .user(user)
                .build();
        boardRepository.save(board);

        BoardWriteResponse boardWriteResponse = BoardWriteResponse.builder()
                .nickname(user.getNickname())
                .title(board.getTitle())
                .subjectType(board.getSubjectType())
                .httpStatus(HttpStatus.OK)
                .build();
        return boardWriteResponse;
    }

    @Transactional
    public BoardEditResponse edit(Long id, BoardEditRequest boardEditRequest) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("없는 글");
        });
        User user = userRepository.findByUsernameAndEmail(boardEditRequest.getUsername(), boardEditRequest.getEmail());
        if(user == null) {
            BoardEditResponse boardEditResponse = BoardEditResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
            return boardEditResponse;
        }
        board.editBoard(boardEditRequest.getTitle(), boardEditRequest.getContent(), boardEditRequest.getAnswer(),
                boardEditRequest.getExplanation(), boardEditRequest.getMajorType(), boardEditRequest.getSubjectType());

        BoardEditResponse boardEditResponse = BoardEditResponse.builder()
                .nickname(user.getNickname())
                .title(board.getTitle())
                .subjectType(board.getSubjectType())
                .httpStatus(HttpStatus.OK)
                .build();
        return boardEditResponse;
    }

    @Transactional
    public boolean isUserWriteBoard(Long boardId, String username, String email) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> {
            return new IllegalArgumentException("없는 글");
        });
        User user = userRepository.findByUsernameAndEmail(username, email);
        if(user == null) return false;
        if(board.getUser().getId() == user.getId()) return true;
        return false;
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

    @Transactional
    public void viewCountUp(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("없는 글");
        });
        board.increaseViewCount();
    }

    private boolean validateBoardWrite(BoardWriteRequest boardWriteRequest) {
        if (isNullAndEmpty(boardWriteRequest.getContent()) || isNullAndEmpty(boardWriteRequest.getAnswer())) {
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
