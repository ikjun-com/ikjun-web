package com.ikjunweb.controller;

import com.ikjunweb.config.auth.PrincipalDetail;
import com.ikjunweb.entity.board.Board;
import com.ikjunweb.entity.board.BoardLike;
import com.ikjunweb.service.BoardLikeService;
import com.ikjunweb.service.BoardService;
import com.ikjunweb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class UserController {

    private final UserService userService;
    private final BoardLikeService boardLikeService;

    @Autowired
    public UserController(UserService userService, BoardLikeService boardLikeService) {
        this.userService = userService;
        this.boardLikeService = boardLikeService;
    }

    @GetMapping("/ikjun/registerForm")
    public String registerForm() {
        return "registerForm";
    }

    @GetMapping("/ikjun/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/ikjun/user/myprofile")
    public String userDetail(@AuthenticationPrincipal PrincipalDetail principalDetail, Model model) {
        List<BoardLike> likes = boardLikeService.getUserLike(principalDetail.getId());
        model.addAttribute("likes", likes);
        return "/user/myprofile";
    }

    @ResponseBody
    @PostMapping("/ikjun/loginForm/usernameCheck")
    public boolean usernameCheck(@RequestParam("username") String username) {
        boolean result = userService.isUsernameOverlap(username);
        return result;
    }

    @ResponseBody
    @PostMapping("/ikjun/loginForm/emailCheck")
    public boolean emailCheck(@RequestParam("email") String email) {
        boolean result = userService.isEmailOverLap(email);
        return result;
    }

    @ResponseBody
    @PostMapping("/ikjun/loginForm/nicknameCheck")
    public boolean nickNameCheck(@RequestParam("nickname") String nickname) {
        boolean result = userService.isNicknameOverLap(nickname);
        return result;
    }
}