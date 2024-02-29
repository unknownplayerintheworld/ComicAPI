package com.hung.comicapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name="history_comic")
public class History_Comic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hcID;

    private int comicIDfk;

    @Column(nullable = false)
    private Timestamp historyreading;

    private int historyIDfk;
}
