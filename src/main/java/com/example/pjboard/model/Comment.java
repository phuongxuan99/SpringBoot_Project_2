package com.example.pjboard.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;// 댓글 내용


    @Column(nullable = false)
    private String password;


    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment(String content, String name) {
    }

    public Comment(String content, String password, Post post) {
        this.content = content;
        this.password = password;
        this.post = post;
    }
}
