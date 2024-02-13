package com.hung.comicapi.model;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_chapterID", referencedColumnName = "chapterID")
    private chapter chapter;
}
