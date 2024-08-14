package com.example.pjboard.Service;

import com.example.pjboard.model.Board;
import com.example.pjboard.repo.BoardRepository;
import com.example.pjboard.repo.CommentRepository;
import com.example.pjboard.repo.PostRepository;
import com.example.pjboard.model.Comment;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service

public class CommentService {
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentService(
            BoardRepository boardRepository,
            PostRepository postRepository,
            CommentRepository commentRepository
    ){
        this.boardRepository = boardRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    // Create
    public Comment create(
            Long boardId,
            Long postId,
            String content,
            String name
    ){
        Board board = boardRepository.findById(boardId)
                .orElseThrow();
        Comment comment = new Comment(
                content,
                name
        );
        return commentRepository.save(comment);
    }

    public Comment readOne(
            Long commentId
    ){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow();
        return comment;
    }


    public Comment update(Long id,
                          String content
    ) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow();
        return commentRepository.save(comment);
    }

    public void delete(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new NoSuchElementException("Comment not found with id: " + id);
        }
        commentRepository.deleteById(id);
    }
}
