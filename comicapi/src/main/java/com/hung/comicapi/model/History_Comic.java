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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comicIDfk", referencedColumnName = "comicID")
    private Comic comic;

    @Column(nullable = false)
    private Timestamp historyreading;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "historyIDfk", referencedColumnName = "historyID")
    private History history;
}
