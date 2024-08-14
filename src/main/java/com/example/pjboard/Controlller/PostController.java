package com.example.pjboard.Controlller;

import com.example.pjboard.Service.PostService;
import com.example.pjboard.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/article")
public class PostController {
    @Autowired
  //  private PostRepository postRepository;
    private PostService postService;

    //상세 보기
    @GetMapping("/{id}")
    public String viewPost(@PathVariable("id") Long id, Model model) {
        Post post = postService.findPostById(id);
        model.addAttribute("post", post);
        return "article/view.html";
    }


    @PostMapping("{id}/delete")
    public String deletePost(
            @PathVariable("id")
            Long id
    ){
        postService.deletePost(id);
        return "redirect:/article";
    }

    //update
    @PostMapping("/update/{id}")
    public String updatePost(
            @PathVariable("id")
            Long id,
           @RequestParam("content")
            String content,
            @RequestParam("password")
            String password
    ) {
        postService.updatePost(id, content,password, password);
        return String.format("redirect:/article/%d", id);
    }

    @GetMapping("/hashtags")
    public String listHashtags(Model model) {
        List<String> hashtags = postService.getAllHashtags();
        model.addAttribute("hashtags", hashtags);
        return "article/hashtags.html";
    }
}



