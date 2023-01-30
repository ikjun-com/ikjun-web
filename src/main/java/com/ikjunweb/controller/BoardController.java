package com.ikjunweb.controller;

import com.ikjunweb.config.auth.PrincipalDetail;
import com.ikjunweb.entity.SearchCondition;
import com.ikjunweb.entity.board.Board;
import com.ikjunweb.entity.type.MajorType;
import com.ikjunweb.entity.type.SortType;
import com.ikjunweb.entity.type.SubjectType;
import com.ikjunweb.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.ikjunweb.entity.board.QBoard.board;

@Slf4j
@Controller
public class BoardController {

    private final BoardService boardService;

    Comparator<Board> popularSort = new Comparator<Board>() {
        @Override
        public int compare(Board o1, Board o2) {
            return o1.getViewCount() - o2.getViewCount();
        }
    };
    Comparator<Board> recentSort = new Comparator<Board>() {
        @Override
        public int compare(Board o1, Board o2) {
            return o2.getCreateDate().compareTo(o1.getCreateDate());
        }
    };

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/ikjun")
    public String home(Authentication authentication, Model model, HttpSession session,
                       MajorType majorType, SubjectType subjectType, SortType sortType, String keyword) {
        Map<MajorType, String> majorTypes = MajorType.getMajorTypeMap();
        Map<SubjectType, String> subjectTypes = SubjectType.getSubjectTypeMap();
        Map<SortType, String> sortTypes = SortType.getSortTypeMap();
        model.addAttribute("majorTypes", majorTypes);
        model.addAttribute("subjectTypes", subjectTypes);
        model.addAttribute("sortTypes", sortTypes);
        model.addAttribute("keyword", keyword);

        List<Board> boards = null;
        if (majorType == null && subjectType == null && keyword == null) {
            boards = boardService.findAll();
        } else {
            boards = boardService.searchBoard(new SearchCondition(keyword, majorType, subjectType));
        }

        if (sortType == SortType.POPULAR) {
            Collections.sort(boards, popularSort);
        } else {
            Collections.sort(boards, recentSort);
        }

        model.addAttribute("boards", boards);
        if(authentication != null) {
            PrincipalDetail principalDetail = (PrincipalDetail) authentication.getPrincipal();
            String nickname = principalDetail.getNickname();
            session.setAttribute("nickname", nickname);
            model.addAttribute("nickname", nickname);
        }
        return "home";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/ikjun";
    }

    @GetMapping("/ikjun/board/writeForm")
    public String writeForm(Model model) {
        Map<MajorType, String> majorTypes = MajorType.getMajorTypeMap();
        Map<SubjectType, String> subjectTypes = SubjectType.getSubjectTypeMap();
        model.addAttribute("majorTypes", majorTypes);
        model.addAttribute("subjectTypes", subjectTypes);

        return "board/writeForm";
    }

    @GetMapping("/ikjun/board/{boardId}")
    public String boardDetail(@PathVariable Long boardId, Model model, HttpServletRequest request, HttpServletResponse response,
                              @AuthenticationPrincipal PrincipalDetail principalDetail) {
        Board board = boardService.findBoard(boardId);
        boolean author = boardService.isUserWriteBoard(boardId, principalDetail.getUsername(), principalDetail.getEmail());
        String major = MajorType.getMajorType(board.getMajorType());
        String subject = SubjectType.getSubjectType(board.getSubjectType());

        model.addAttribute("board", board);
        model.addAttribute("author", author);
        model.addAttribute("major", major);
        model.addAttribute("subject", subject);

        viewCountUp(boardId, request, response);
        return "board/boardDetail";
    }

    private void viewCountUp(Long id, HttpServletRequest request, HttpServletResponse response) {
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("postView")) {
                    oldCookie = cookie;
                }
            }
        }

        if (oldCookie != null) {
            if (!oldCookie.getValue().contains("[" + id.toString() + "]")) {
                boardService.viewCountUp(id);
                oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(oldCookie);
            }
        } else {
            boardService.viewCountUp(id);
            Cookie newCookie = new Cookie("postView","[" + id + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(newCookie);
        }
    }
}
