package com.hung.comicapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="comic_trans")
public class comic_trans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comictransID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comicIDfk", referencedColumnName = "comicID")
    private Comic comic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transIDfk", referencedColumnName = "transteamID")
    private transteam transteam;
}
