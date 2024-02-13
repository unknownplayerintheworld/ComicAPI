package com.hung.comicapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name="comic")
public class Comic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comicID;

    @Column(nullable = false)
    private String comicName;

    private String avatarLink;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int views;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int views_in_month;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int views_in_week;

    @Column(nullable = false)
    private Timestamp created_at;

    @Column(nullable = false)
    private Timestamp updated_at;
}
