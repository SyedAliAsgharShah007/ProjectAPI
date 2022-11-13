package com.example.securityServices.services;

import com.example.securityServices.controller.ApiController;
import com.example.securityServices.dataObject.CommentObject;
import com.example.securityServices.dataObject.PostObject;
import com.example.securityServices.dataObject.UserObject;
import com.example.securityServices.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service @RequiredArgsConstructor
public class ServiceLayer {

    private final RestTemplate restTemplate;

//    @Autowired
//    public ServiceLayer(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    public CommentObject consumeAPI()
    {

        return restTemplate.getForObject("https://gorest.co.in/public/v2/posts/29/comments", CommentObject.class);
    }

    public PostObject postObject()
    {
        return restTemplate.getForObject("https://gorest.co.in/public/v2/posts/29/comments", PostObject.class);
    }

    public UserObject userObject()
    {
        return restTemplate.getForObject("https://gorest.co.in/public/v2/posts/29/comments", UserObject.class);
    }

}
