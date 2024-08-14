package com.example.pjboard.Service;

import com.example.pjboard.model.Post;
import com.example.pjboard.repo.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Service
public class PostService {

    private final PostRepository postRepository;



    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    // 상세보기
    public Post findPostById(Long postId) {
        return  postRepository.findById(postId)
                .orElseThrow();
    }

    //update
    public Post updatePost(Long id, Post updatedPost
    ) {
        return postRepository.findById(id)
                .map(existingPost -> {
                    existingPost.setTitle(updatedPost.getTitle());
                    existingPost.setContent(updatedPost.getContent());
                    existingPost.setPassword(updatedPost.getPassword());
                    return postRepository.save(existingPost);
                })
                .orElseThrow();
    }

    //삭제
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }


    public void updatePost(Long id, String content) {
    }
}

