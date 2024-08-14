package com.example.pjboard.repo;

import com.example.pjboard.Controlller.HashtagController;
import com.example.pjboard.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByPostId(Long PostId);

    Post findTopByIdGreaterThanOrderByIdAsc(Long id);
    Post findTopByIdLessThanOrderByIdDesc(Long id);
    Post updatePost(Long id, Post updatedPost);

    List<HashtagController.Hashtag> getAllHashtags();

}