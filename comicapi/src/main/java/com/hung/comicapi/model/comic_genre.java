package com.hung.comicapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="comic_genre")
public class comic_genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comicgenreID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comicIDfk", referencedColumnName = "comicID")
    private Comic comic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genreIDfk", referencedColumnName = "genreID")
    private Genre genre;
}
