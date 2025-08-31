package com.example.toy_project_social.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    int post_id;

    @Column
    String content;

    @Column
    LocalDateTime created_time;

    @Column
    int author_id;

    public Comment() {

    }
}
