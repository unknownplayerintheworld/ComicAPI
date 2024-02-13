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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comicIDfk", referencedColumnName = "comicID")
    private Comic comic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "favouriteIDfk", referencedColumnName = "favouriteID")
    private favourite favourite;
}
