package com.example.toy_project_social.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDto {
    private String email;
    private String password;
    private String name;
    private String phone;
}