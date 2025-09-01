package com.example.toy_project_social.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateDto {
    private Integer id;
    private String title;
    private String content;
    private Integer author_id;
}