package com.example.toy_project_social.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String password;

    @Column
    String name;

    @Column
    String phone;

    @Column
    String email;

    public User() {

    }
}
