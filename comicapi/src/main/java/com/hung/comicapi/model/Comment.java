package com.hung.comicapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentID;
    @Column(name = "parent_commentID",nullable = true)
    private Integer parent_commentID;
    @Column(name = "content",nullable = false)
    private String content;
    @Column(name = "likeCount",nullable = false,columnDefinition = "int default 0")
    private int like;
    @Column(name = "dislikeCount",nullable = false,columnDefinition = "int default 0")
    private int dislike;
    @Column(name ="updated_at",nullable = false)
    private Timestamp updated_at;
    @Column(name = "accountID",nullable = false)
    private Integer accountID;
    @Column(name = "chapterID",nullable = true)
    private Integer chapterID;
    @Column(name = "comicID",nullable = false)
    private int comicID;
}
