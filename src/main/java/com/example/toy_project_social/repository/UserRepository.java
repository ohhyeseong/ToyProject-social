package com.example.toy_project_social.repository;

import com.example.toy_project_social.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
