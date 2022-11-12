package com.example.securityServices.repository;

import com.example.securityServices.domain.Comment;
import com.example.securityServices.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByName(String comment);

}
