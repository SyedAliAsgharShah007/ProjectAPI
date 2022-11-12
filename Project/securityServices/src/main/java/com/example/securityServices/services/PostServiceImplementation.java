package com.example.securityServices.services;

import com.example.securityServices.domain.Post;
import com.example.securityServices.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostServiceImplementation implements PostServices {
    private final PostRepository postRepository;

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getPosts()
    {
        return postRepository.findAll();
    }
}
