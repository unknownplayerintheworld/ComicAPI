package com.hung.comicapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="favourite")
public class favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int favouriteID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountID", referencedColumnName = "id")
    private Account account;
}
