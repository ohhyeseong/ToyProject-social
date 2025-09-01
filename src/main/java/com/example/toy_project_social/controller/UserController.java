package com.example.toy_project_social.controller;

import com.example.toy_project_social.dto.UserCreateDto;
import com.example.toy_project_social.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signupForm() {
        return "signup_form"; // templates/signup_form.html 렌더링
    }

    @PostMapping("/signup")
    public String signup(UserCreateDto userCreateDto, Model model) {
        try {
            userService.create(userCreateDto.getEmail(), userCreateDto.getPassword(), userCreateDto.getName(), userCreateDto.getPhone());
        } catch (IllegalStateException e) {
            // 예외가 발생하면 에러 메시지를 모델에 담아서 뷰로 전달합니다.
            model.addAttribute("errorMessage", e.getMessage());
            return "signup_form";
        }
        return "redirect:/user/login"; // 회원가입 성공 시 로그인 페이지로 리다이렉트
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login_form"; // templates/login_form.html 렌더링
    }
}