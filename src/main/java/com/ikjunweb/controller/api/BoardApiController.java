package com.ikjunweb.controller.api;

import com.ikjunweb.config.auth.PrincipalDetail;
import com.ikjunweb.requestdto.board.BoardEditRequest;
import com.ikjunweb.requestdto.board.BoardLikeRequest;
import com.ikjunweb.requestdto.board.BoardWriteRequest;
import com.ikjunweb.responsedto.board.BoardEditResponse;
import com.ikjunweb.responsedto.board.BoardWriteResponse;
import com.ikjunweb.service.BoardLikeService;
import com.ikjunweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BoardApiController {

    private final BoardService boardService;
    private final BoardLikeService boardLikeService;

    @Autowired
    public BoardApiController(BoardService boardService, BoardLikeService boardLikeService) {
        this.boardService = boardService;
        this.boardLikeService = boardLikeService;
    }

    @PostMapping("/ikjun/board/write")
    public ResponseEntity<BoardWriteResponse> write(@AuthenticationPrincipal PrincipalDetail principalDetail, @RequestBody BoardWriteRequest boardWriteRequest) {
        boardWriteRequest.setUsername(principalDetail.getUsername());
        boardWriteRequest.setEmail(principalDetail.getEmail());
        BoardWriteResponse boardWriteResponse = boardService.write(boardWriteRequest);

        return new ResponseEntity<>(boardWriteResponse, boardWriteResponse.getHttpStatus());
    }

    @PutMapping("/ikjun/board/{boardId}")
    public ResponseEntity<BoardEditResponse> edit(@PathVariable Long boardId, @RequestBody BoardEditRequest boardEditRequest,
                                                  @AuthenticationPrincipal PrincipalDetail principalDetail, Model model) {
        boardEditRequest.setUsername(principalDetail.getUsername());
        boardEditRequest.setEmail(principalDetail.getEmail());
        model.addAttribute("boardId", boardId);
        model.addAttribute("likeCount", boardLikeService.getPostLikeNum(boardId));
        BoardEditResponse boardEditResponse = boardService.edit(boardId, boardEditRequest);

        return new ResponseEntity<>(boardEditResponse, boardEditResponse.getHttpStatus());
    }

    @PostMapping("/ikjun/board/like/{boardId}")
    public String pushLike(@AuthenticationPrincipal PrincipalDetail principalDetail, @PathVariable Long boardId,
                           @ModelAttribute BoardLikeRequest boardLikeRequest) {
        boardLikeRequest = BoardLikeRequest.builder().user(principalDetail.getUser()).boardId(boardId).build();
        boardLikeService.pushLikeButton(boardLikeRequest);

        return "redirect:/ikjun/board/" + boardId;
    }
}