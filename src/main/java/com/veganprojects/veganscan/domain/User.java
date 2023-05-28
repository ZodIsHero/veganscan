package com.veganprojects.veganscan.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "Users")
public class User {
    @Id
    private UUID uuid;

    @Column(name = "name")
    private String name;
}
