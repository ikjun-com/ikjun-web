package com.ikjunweb.controller;

import com.ikjunweb.config.auth.PrincipalDetails;
import com.ikjunweb.entity.board.Board;
import com.ikjunweb.entity.user.User;
import com.ikjunweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String home(Authentication authentication, Model model, HttpSession session) throws NullPointerException{
        boolean isLogined;
        User user = null;
        if(authentication == null) {
            isLogined = false;
            model.addAttribute("user", user);
            model.addAttribute("isLogined", isLogined);
        } else {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            user = principalDetails.getUser();
            isLogined = true;
            model.addAttribute("user", user);
            model.addAttribute("isLogined", isLogined);
            session.setAttribute("user", user);
        }

        return "home";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/ikjun/board/writeForm")
    public String writeForm(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        model.addAttribute("username", principalDetails.getUsername());
        model.addAttribute("email", principalDetails.getEmail());
        return "board/writeForm";
    }

    @GetMapping("/ikjun/board/{id}")
    public String boardDetail(@PathVariable Long id) {
        Board board = boardService.findBoard(id);
        return "/board/boardDetail";
    }
}
