package com.example.pjboard.Service;

import com.example.pjboard.dto.ArticleDto;
import com.example.pjboard.model.Article;
import com.example.pjboard.model.ArticleHashtag;
import com.example.pjboard.model.Board;
import com.example.pjboard.model.HashTag;
import com.example.pjboard.repo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final BoardRepository boardRepository;
    private final ArticleRepository articleRepository;
    private final HashTagRepository hashTagRepository;
    private final ArticleHashtagRepository articleHashtagRepository;
    private final ArticleImageRepository articleImageRepository;

    public ArticleDto create(Long boardId, ArticleDto dto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow();
        Article article = new Article();
        article.setBoard(board);
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setPassword(dto.getPassword());
        article = articleRepository.save(article);
        for (HashTag hashTag: createHashTags(article.getContent())) {
            articleHashtagRepository.save(new ArticleHashtag(article, hashTag));
        }
        return ArticleDto.fromEntity(articleRepository.save(article));
    }

    public List<ArticleDto> readAll() {
        List<ArticleDto> articleDtos = new ArrayList<>();
        for (Article article: articleRepository.findAll()) {
            articleDtos.add(ArticleDto.fromEntity(article));
        }
        return articleDtos;
    }

    public ArticleDto readArticle(Long id) {
        return ArticleDto.fromEntity(articleRepository.findById(id)
                .orElseThrow());
    }

    public ArticleDto update(Long id, ArticleDto articleDto) {
        Article article = articleRepository.findById(id)
                .orElseThrow();
        if (article.getPassword().equals(articleDto.getPassword())) {
            article.setTitle(articleDto.getTitle());
            article.setContent(articleDto.getContent());
        }
        // TODO else에서 throw
        return ArticleDto.fromEntity(articleRepository.save(article));
    }

    public void delete(Long id, String password) {
        Article article = articleRepository.findById(id)
                .orElseThrow();
        if (article.getPassword().equals(password)) {
            articleRepository.delete(article);
        }
        // TODO else에서 throw
    }

    public List<ArticleDto> byTag(String tag) {
        HashTag hashTag = hashTagRepository.findByTag(tag)
                .orElseThrow();

        List<ArticleDto> articles = new ArrayList<>();
        for (ArticleHashtag joinEntity: hashTag.getArticleHashtag()) {
            articles.add(ArticleDto.fromEntity(joinEntity.getArticle()));
        }

        return articles;
    }

    public List<ArticleDto> search(Long boardId, String criteria, String query) {
        List<ArticleDto> results = new ArrayList<>();
        List<Article> articles;
        if (boardId == 0L) {
            articles = criteria.equals("title")
                    ? articleRepository.findByTitleContains(query)
                    : articleRepository.findByContentContains(query);
        } else {
            articles = criteria.equals("title")
                    ? articleRepository.findByTitleContainsAndBoardId(query, boardId)
                    : articleRepository.findByContentContainsAndBoardId(query, boardId);
        }

        for (Article article: articles) {
            results.add(ArticleDto.fromEntity(article));
        }

        return results;
    }

    public Long getFront(Long boardId, Long articleId) {
        Optional<Article> target;
        if (boardId == 0L) {
            target = articleRepository.findFirstByIdAfter(articleId);
        } else {
            target = articleRepository.findFirstByBoardIdAndIdAfter(boardId, articleId);
        }
        return target.map(Article::getId).orElse(null);
    }

    public Long getBack(Long boardId, Long articleId) {
        Optional<Article> target;
        if (boardId == 0L) {
            target = articleRepository.findFirstByIdBeforeOrderByIdDesc(articleId);
        } else {
            target = articleRepository.findFirstByBoardIdAndIdBeforeOrderByIdDesc(boardId, articleId);
        }
        return target.map(Article::getId).orElse(null);
    }

    private Set<HashTag> createHashTags(String content) {
        String[] words = content.split(" ");
        Set<HashTag> hashTags = new HashSet<>();
        for (String word: words) {
            if (word.startsWith("#")) {
                Optional<HashTag> hashTagOptional = hashTagRepository.findByTag(word);
                if (hashTagOptional.isPresent()) hashTags.add(hashTagOptional.get());
                else hashTags.add(hashTagRepository.save(new HashTag(word)));
            }
        }

        return hashTags;
    }
}