package com.example.securityServices.services;

import com.example.securityServices.domain.Comment;
import com.example.securityServices.domain.Post;
import com.example.securityServices.repository.CommentRepository;
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
public class CommentServiceImplementation implements CommentServices {
    private final CommentRepository commentRepository;

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getComment()
    {
        return commentRepository.findAll();
    }
}
