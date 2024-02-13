package com.hung.comicapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name="chapter")
public class chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chapterID;

    private int chapter_number_pos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_comicID", referencedColumnName = "comicID")
    private Comic comic;

    @Column(nullable = false)
    private Timestamp updated_at;
}
