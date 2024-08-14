package com.example.pjboard.Service;

import com.example.pjboard.model.Post;
import com.example.pjboard.repo.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PostService {
    private final PostRepository postRepository;
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    // 상세보기
    public Post findPostById(Long id) {
        return  postRepository.findById(id)
                .orElseThrow();
    }

    //삭제
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    //update
    public boolean updatePost(Long id, String title, String content, String password) {
        Optional<Post> existingPostOptional = postRepository.findById(id);
        if (existingPostOptional.isPresent()) {
            Post existingPost = existingPostOptional.get();
            existingPost.setTitle(title);
            existingPost.setContent(content);
            existingPost.setPassword(password);
            postRepository.save(existingPost);
            return true; // Return true if update was successful
        } else {
            return false; // Return false if post was not found
        }
    }

    // hashtag
    public List<String> getAllHashtags() {
        return postRepository.getAllHashtags();
    }
}

