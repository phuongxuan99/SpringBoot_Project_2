package com.example.pjboard.Controlller;

import com.example.pjboard.Service.PostService;
import com.example.pjboard.model.Post;
import com.example.pjboard.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/article")
public class PostController {
    @Autowired
    private PostRepository postRepository;
    private PostService postService;

    //상세 보기
    @GetMapping("/{postId}")
    public String viewPost(@PathVariable("postId") Long postId, Model model) {
        Post post = postService.findPostById(postId);
        model.addAttribute("post", post);
        return "article/view.html";
    }


    @PostMapping("{id}/delete")
    public String deletePost(
            @PathVariable("postId")
            Long id
    ){
        postService.deletePost(id);
        return "redirect:/article";
    }

    //update
    @PostMapping("/update/{id}")
    public String updatePost(
            @PathVariable("id") Long id,
           @RequestParam("content")
            String content
    ) {
        postService.updatePost(id, content);
        return String.format("redirect:/article/%d", id);
    }
}



//
//
//    // 도전기능
//
//    @GetMapping("/article/{id}")
//    private String showPost(@PathVariable Long id, Model model){
//        // 현재 게시글 조회
//        Post post = postRepository.findById(id).orElse(null);
//        if (post == null) {
//            return "error/404"; // 게시글을 찾을 수 없는 경우 404 페이지로 이동
//        }
//        // 이전글과 다음글 찾기
//        Post previousPost = postRepository.findTopByIdGreaterThanOrderByIdAsc(id);
//        Post nextPost = postRepository.findTopByIdLessThanOrderByIdDesc(id);
//
//        // 링크 URL 설정
//        String previousPostUrl = previousPost != null ? "/post/" + previousPost.getId() : null;
//        String nextPostUrl = nextPost != null ? "/post/" + nextPost.getId() : null;
//
//        // 모델에 데이터 추가
//        model.addAttribute("post", post);
//        model.addAttribute("previousPostUrl", previousPostUrl);
//        model.addAttribute("nextPostUrl", nextPostUrl);
//
//        return "post_detail.html";
//
//    }

//    private final PostService postService;
//
//    public PostController(PostService postService) {
//        this.postService = postService;
//    }
//
//    @PostMapping
//    public ResponseEntity<Post> createPost( @RequestBody Post post) {
//        try {
//            Post createdPost = postService.savePost(post);
//            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Post>> getAllPosts() {
//        try {
//            List<Post> posts = postService.getAllPosts();
//            return new ResponseEntity<>(posts, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
//        try {
//            Post post = postService.getPostById(id);
//            return new ResponseEntity<>(post, HttpStatus.OK);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
//        try {
//            Post savedPost = postService.updatePost(id, updatedPost);
//            return new ResponseEntity<>(savedPost, HttpStatus.OK);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletePost(@PathVariable Long id, @RequestParam String password) {
//        try {
//            postService.deletePost(id, password);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
