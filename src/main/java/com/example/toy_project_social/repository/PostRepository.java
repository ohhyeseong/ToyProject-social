package com.example.toy_project_social.repository;

import com.example.toy_project_social.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
