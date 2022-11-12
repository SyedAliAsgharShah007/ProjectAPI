package com.example.securityServices.services;

import com.example.securityServices.domain.AppUser;
import com.example.securityServices.domain.Comment;
import com.example.securityServices.domain.Post;
import com.example.securityServices.domain.Role;

import java.util.List;

public interface CommentServices {
    Comment saveComment(Comment comment);
    List<Comment> getComment();
}
