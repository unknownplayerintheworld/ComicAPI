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

    private int accountID;
    public favourite(int favouriteID,int accountID){
        this.accountID = accountID;
        this.favouriteID = favouriteID;
    }
    public favourite(){}
}
