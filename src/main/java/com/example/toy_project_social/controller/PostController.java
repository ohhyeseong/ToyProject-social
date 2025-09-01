package com.example.toy_project_social.controller;

import com.example.toy_project_social.dto.PostCreateDto;
import com.example.toy_project_social.entity.Post;
import com.example.toy_project_social.entity.SiteUser;
import com.example.toy_project_social.service.PostService;
import com.example.toy_project_social.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 생성해줍니다.
public class PostController {

    private final PostService postService;
    private final UserService userService;

    // @RequiredArgsConstructor 어노테이션이 아래 생성자 코드를 대체합니다.
    // public PostController(PostService postService) {
    //     this.postService = postService;
    // }

    @GetMapping("/create")
    public String createPostForm() {

        return "create_post";
    }

    @PostMapping("/create")
    public String createPost(PostCreateDto dto, Principal principal) {
        // 1. Principal 객체에서 현재 로그인한 사용자의 이메일(username)을 가져옵니다.
        String email = principal.getName();
        // 2. 이메일을 사용해 DB에서 실제 User 객체를 조회합니다.
        SiteUser author = this.userService.getUser(email);
        // 3. DTO와 조회한 User 객체를 서비스로 전달하여 게시글을 생성합니다.
        this.postService.createPost(dto, author);
        return "redirect:/post/list";
    }

    @GetMapping("/list")
    public String listPosts(Model model) {
        // 1. Service로부터 데이터(게시글 목록)를 직접 받습니다.
        List<Post> posts = this.postService.listPosts();
        // 2. Controller가 Model에 데이터를 추가합니다.
        model.addAttribute("posts", posts);
        return "list";
    }

    @GetMapping("/detail/{id}")
    public String detailPost(@PathVariable("id") Integer id, Model model) {
        // 1. Service로부터 특정 게시글 데이터를 받습니다.
        Post post = this.postService.detailPost(id);
        // 2. Controller가 Model에 데이터를 추가합니다.
        model.addAttribute("post", post);
        return "detail";
    }

    @GetMapping("/fix/{id}")
    public String fixPost(@PathVariable("id") Integer id, Model model) {
        // 수정할 기존 데이터를 조회하기 위해 Service를 호출합니다.
        Post post = this.postService.detailPost(id);
        model.addAttribute("post", post);
        return "fix_post";
    }

    @PostMapping("/fix")
    public String updatePost(PostCreateDto dto, Principal principal) {
        Post post = this.postService.detailPost(dto.getId());
        // 현재 로그인한 사용자가 게시글의 작성자인지 확인
        if (!post.getAuthor().getEmail().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }

        // 수정 권한이 있다면 서비스 호출
        this.postService.updatePost(dto.getId(), dto.getTitle(), dto.getContent());

        return "redirect:/post/detail/" + dto.getId();
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") Integer id) {
        this.postService.deletePost(id);
        return "redirect:/post/list";
    }
}