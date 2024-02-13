package com.hung.comicapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int historyID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountID", referencedColumnName = "id")
    private Account account;
}
