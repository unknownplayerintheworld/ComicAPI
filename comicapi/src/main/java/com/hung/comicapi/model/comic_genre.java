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

    @Column(name = "")
    private int comicIDfk;

    private int genreIDfk;
}
