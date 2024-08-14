package com.example.pjboard.model;


import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Getter
@Entity

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String password;

    @Setter
    private String hashtag;

    @CollectionTable(name = "questions_hashtags", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "hashtag")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> hashtags = new HashSet<>();

//    @Setter
//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
//    private List<Comment> comments;

    @Column(name = "board_id")
    public Long boardId;

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }
//    public Post(String title, String content, String password) {
//        this.title = title;
//        this.content = content;
//        this.password = password;
//    }


    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }
}
