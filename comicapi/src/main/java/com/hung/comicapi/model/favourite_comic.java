package com.hung.comicapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="favourite_comic")
public class favourite_comic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fcID;

    private int comicIDfk;

    private int favouriteIDfk;
}
