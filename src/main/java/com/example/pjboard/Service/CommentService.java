package com.example.pjboard.Service;

import com.example.pjboard.dto.CommentDto;
import com.example.pjboard.model.Article;
import com.example.pjboard.model.Board;
import com.example.pjboard.repo.ArticleRepository;
import com.example.pjboard.repo.BoardRepository;
import com.example.pjboard.repo.CommentRepository;
import com.example.pjboard.model.Comment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public CommentDto createComment(Long articleId, CommentDto dto) {
        Article article = articleRepository.findById(articleId).orElseThrow();

        Comment comment = new Comment();
        comment.setArticle(article);
        comment.setContent(dto.getContent());
        comment.setPassword(dto.getPassword());
        return CommentDto.fromEntity(commentRepository.save(comment));
    }

    public void deleteComment(Long id, String password) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow();
        if (comment.getPassword().equals(password)) {
            commentRepository.delete(comment);
        }
        // TODO else에서 throw
    }
}