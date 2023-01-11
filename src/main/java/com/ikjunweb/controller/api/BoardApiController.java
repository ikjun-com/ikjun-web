package com.ikjunweb.controller.api;

import com.ikjunweb.config.auth.PrincipalDetails;
import com.ikjunweb.repository.BoardLikeRepository;
import com.ikjunweb.requestdto.board.BoardLikeRequest;
import com.ikjunweb.requestdto.board.BoardWriteRequest;
import com.ikjunweb.responsedto.board.BoardLikeResponse;
import com.ikjunweb.responsedto.board.BoardWriteResponse;
import com.ikjunweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public BoardApiController(BoardService boardService,
                              BoardLikeRepository boardLikeRepository) {
        this.boardService = boardService;
        this.boardLikeRepository = boardLikeRepository;
    }

    @PostMapping("/ikjun/board/write")
    public ResponseEntity<BoardWriteResponse> write(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody BoardWriteRequest boardWriteRequest) {
        boardWriteRequest.setUsername(principalDetails.getUsername());
        boardWriteRequest.setEmail(principalDetails.getEmail());
        BoardWriteResponse boardWriteResponse = boardService.write(boardWriteRequest);
        if(boardWriteResponse.getHttpStatus() != HttpStatus.OK) return new ResponseEntity<>(boardWriteResponse, boardWriteResponse.getHttpStatus());

        return new ResponseEntity<>(boardWriteResponse, boardWriteResponse.getHttpStatus());
    }

    @PostMapping("/ikjun/board/{boardId}/like")
    public ResponseEntity<BoardLikeResponse> onClickLike(@PathVariable Long boardId, @RequestBody BoardLikeRequest boardLikeRequest,
                                                         @AuthenticationPrincipal PrincipalDetails principalDetails) {
        boardLikeRequest.setUsername(principalDetails.getUsername());
        boardLikeRequest.setEmail(principalDetails.getEmail());
    }
}
