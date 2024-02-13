package com.hung.comicapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="genre")
public class Genre {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int genreID;
    @Column(nullable = false)
    private String genrename;
    private String description;
}
