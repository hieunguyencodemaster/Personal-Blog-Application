package com.example.Blog.Web.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(length = 5000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User postedBy;

    private String img;

    private Date date;

    private int likeCount;

    private int viewCount;

    private List<String> tags;
}
