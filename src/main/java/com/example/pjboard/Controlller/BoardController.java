package com.example.pjboard.Controlller;


import com.example.pjboard.Service.BoardService;
import com.example.pjboard.Service.PostService;
import com.example.pjboard.model.Board;
import com.example.pjboard.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("boards")
public class BoardController {
    private final BoardService boardService;
   // private final PostService postService;

    public BoardController(BoardService boardService, PostService postService) {
        this.boardService = boardService;
     //   this.postService = postService;
    }

    // 조회
    @GetMapping("/boards")
    public String listBoards(Model model) {
        List<Board> boards = boardService.findAllBoards();
        model.addAttribute("boards", boards);
        return "boards/list";
    }

    // 특정 게시글 상세보기
    @GetMapping("/view/{id}")
    public String viewBoard(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
            Board board = boardService.view(id);
            model.addAttribute("board", board);
            return "redirect:/boards/list"; // Return the view name

    }

//    // 상세보기
//    @GetMapping("/{boardId}")
//    public String listPostsByBoard(@PathVariable("boardId") Long boardId, Model model) {
//        List<Post> posts = postService.findPostsByBoard(boardId);
//        model.addAttribute("posts", posts);
//        model.addAttribute("boardId", boardId);
//        return "boards/list";
//    }


    //삭제
    @DeleteMapping("/{id}")
    public String deleteBoard(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            boardService.deleteBoard(id);
            redirectAttributes.addFlashAttribute("message", "Board successfully deleted.");
            return "redirect:/boards"; // Redirect to the list of boards
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred while deleting the board.");
            return "redirect:/boards"; // Redirect to the list of boards
        }
    }

}