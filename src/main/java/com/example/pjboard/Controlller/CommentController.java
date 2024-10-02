package com.example.pjboard.Controlller;

import com.example.pjboard.Service.CommentService;
import com.example.pjboard.dto.CommentDto;
import com.example.pjboard.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/articles/{articleId}")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable Long articleId,
            @RequestBody CommentDto commentDto) {
        CommentDto createdComment = commentService.createComment(articleId, commentDto);
        return ResponseEntity.ok(createdComment);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            @RequestParam String password) {
        commentService.deleteComment(commentId, password);
        return ResponseEntity.noContent().build();
    }
}
