package com.example.toy_project_social.controller;

import com.example.toy_project_social.dto.PostCreateDto;
import com.example.toy_project_social.entity.Post;
import com.example.toy_project_social.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/post")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/create")
    public String createPostForm() {

        return "create_post";
    }

    @PostMapping("/create")
    public String createPost(PostCreateDto dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setAuthor_id(dto.getAuthor_id());

        post.setCreated_time(LocalDateTime.now());
        post.setUpdated_time(LocalDateTime.now());

        this.postRepository.save(post);

        return "redirect:/post/list";
    }

    @GetMapping("/list")
    public String listPosts(Model model) {
        model.addAttribute("posts", this.postRepository.findAll());
        return "list";
    }

    @GetMapping("/detail/{id}")
    public String detailPost(@PathVariable("id") int id, Model model) {
        model.addAttribute("post", this.postRepository.findById(id).get());
        return "detail";
    }
}