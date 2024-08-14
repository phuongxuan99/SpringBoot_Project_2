package com.example.pjboard.Controlller;

import com.example.pjboard.Service.CommentService;
import com.example.pjboard.model.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/comments")
public class CommentController {
//    @Autowired
//    private PostRepository postRepository;
//
//    // GET 요청은 새로운 게시글을 작성할 수 있는 폼을 보여줍니다.
//    @GetMapping("/new")
//    public String showCreateForm(Model model) {
//        model.addAttribute("post", new Post());
//        return "article-create";  // 이 부분은 Thymeleaf 템플릿을 가리킵니다.
//    }
//
//    // POST 요청은 새로운 게시글을 저장합니다.
//    @PostMapping("/new")
//    public String submitPost(Post post) {
//        // 로직 추가: 게시글 저장 로직 등을 처리
//        return "redirect:/articles";  // 게시글 목록으로 리다이렉션
//    }

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Create a new comment
    @PostMapping
    public ResponseEntity<Comment> createComment(
            @RequestParam
            Long boardId,
            @RequestParam
            Long postId,
            @RequestParam
            String content,
            @RequestParam
            String author
    ) {
        Comment createdComment = commentService.create(boardId, postId, content, author);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    // Read a comment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable Long id) {
        try {
            Comment comment = commentService.readOne(id);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update a comment (if needed)
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(
            @PathVariable Long id,
            @RequestParam String content
    ) {
        try {
            Comment updatedComment = commentService.update(id, content);
            return new ResponseEntity<>(updatedComment, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a comment (if needed)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        try {
            commentService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


