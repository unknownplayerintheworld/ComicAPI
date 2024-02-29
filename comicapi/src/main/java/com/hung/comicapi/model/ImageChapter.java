package com.hung.comicapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="imagechapter")
public class ImageChapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageID;

    @Column(nullable = false)
    private String link;

    private int image_chapterID;
    @Column(nullable = false,name = "image_pos")
    private int image_pos;
}
