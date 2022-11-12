package com.example.securityServices.repository;

import com.example.securityServices.domain.Post;
import com.example.securityServices.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitle(String s);
}
