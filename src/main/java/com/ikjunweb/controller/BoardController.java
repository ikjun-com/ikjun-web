package com.ikjunweb.controller;

import com.ikjunweb.config.auth.PrincipalDetails;
import com.ikjunweb.entity.board.Board;
import com.ikjunweb.entity.user.User;
import com.ikjunweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/ikjun")
    public String home(Authentication authentication, Model model, HttpSession session,
                       @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Board> boards = boardService.boardList(pageable);
        model.addAttribute("boards", boards);

        if(authentication != null) {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            String nickname = principalDetails.getNickname();
            session.setAttribute("nickname", nickname);
            model.addAttribute("nickname", nickname);
        }
        return "home";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/ikjun/board/writeForm")
    public String writeForm() {
        return "board/writeForm";
    }

    @GetMapping("/ikjun/board/{boardId}")
    public String boardDetail(@PathVariable Long id, Model model) {
        Board board = boardService.findBoard(id);
        model.addAttribute("board", board);
        return "board/boardDetail";
    }
}
