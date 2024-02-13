package com.hung.comicapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="transteam")
public class transteam {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int transteamID;
    @Column(nullable = false)
    private String transname;
    private String transslogan;
    private String transavatar;
}
