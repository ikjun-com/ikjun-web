package com.ikjunweb.controller.api;

import com.ikjunweb.config.auth.PrincipalDetail;
import com.ikjunweb.repository.BoardLikeRepository;
import com.ikjunweb.requestdto.board.BoardWriteRequest;
import com.ikjunweb.responsedto.board.BoardLikeResponse;
import com.ikjunweb.responsedto.board.BoardWriteResponse;
import com.ikjunweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BoardApiController {

    private final BoardService boardService;
    private final BoardLikeRepository boardLikeRepository;

    @Autowired
    public BoardApiController(BoardService boardService, BoardLikeRepository boardLikeRepository) {
        this.boardService = boardService;
        this.boardLikeRepository = boardLikeRepository;
    }

    @PostMapping("/ikjun/board/write")
    public ResponseEntity<BoardWriteResponse> write(@AuthenticationPrincipal PrincipalDetail principalDetail, @RequestBody BoardWriteRequest boardWriteRequest) {
        boardWriteRequest.setUsername(principalDetail.getUsername());
        boardWriteRequest.setEmail(principalDetail.getEmail());
        BoardWriteResponse boardWriteResponse = boardService.write(boardWriteRequest);

        return new ResponseEntity<>(boardWriteResponse, boardWriteResponse.getHttpStatus());
    }

    @PostMapping("/ikjun/board/like/{boardId}")
    public ResponseEntity<BoardLikeResponse> onClickLike(@PathVariable Long boardId, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        String username = principalDetail.getUsername();
        String email = principalDetail.getEmail();
        BoardLikeResponse boardLikeResponse = boardService.likeCount(boardId, username, email);
        return new ResponseEntity<>(boardLikeResponse, boardLikeResponse.getHttpStatus());
    }
}
