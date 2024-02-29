package com.hung.comicapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name="chapter")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chapterID;

    private int chapter_number_pos;


    @Column(nullable = false)
    private Timestamp updated_at;



    @Column(name = "chapter_comicID")
    private int chapter_comicID;
}
