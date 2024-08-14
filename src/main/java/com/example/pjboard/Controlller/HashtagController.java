package com.example.pjboard.Controlller;


import com.example.pjboard.repo.BoardRepository;
import com.example.pjboard.repo.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HashtagController {
    private final PostRepository postRepository;

    @GetMapping("/hashtags")
    List<Hashtag> getAll() {
        return postRepository.getAllHashtags();
    }

    public class Hashtag {
    }
}
