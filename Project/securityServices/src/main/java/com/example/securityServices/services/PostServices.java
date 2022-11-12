package com.example.securityServices.services;

import com.example.securityServices.domain.AppUser;
import com.example.securityServices.domain.Comment;
import com.example.securityServices.domain.Post;

import java.util.List;

public interface PostServices {
    Post savePost(Post post);
    List<Post> getPosts();
}
