package com.ikjunweb.controller.api;

import com.ikjunweb.requestdto.board.BoardWriteRequest;
import com.ikjunweb.responsedto.board.BoardWriteResponse;
import com.ikjunweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BoardApiController {

    private final BoardService boardService;

    @Autowired
    public BoardApiController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/ikjun/board/write")
    public ResponseEntity<BoardWriteResponse> write(@RequestBody BoardWriteRequest boardWriteRequest) {
        BoardWriteResponse boardWriteResponse = boardService.write(boardWriteRequest);
        if(boardWriteResponse.getHttpStatus() != HttpStatus.OK) return new ResponseEntity<>(boardWriteResponse, boardWriteResponse.getHttpStatus());

        return new ResponseEntity<>(boardWriteResponse, boardWriteResponse.getHttpStatus());
    }
}
