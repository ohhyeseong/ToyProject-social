package com.example.toy_project_social.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String title;

    @Column
    String content;

    @Column
    LocalDateTime created_time;

    @Column
    LocalDateTime updated_time;

    @Column
    int author_id;

    public Post() {

    }
}
