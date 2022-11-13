package com.example.securityServices.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.securityServices.dataObject.CommentObject;
import com.example.securityServices.dataObject.PostObject;
import com.example.securityServices.dataObject.UserObject;
import com.example.securityServices.domain.AppUser;
import com.example.securityServices.domain.Comment;
import com.example.securityServices.domain.Post;
import com.example.securityServices.domain.Role;
import com.example.securityServices.services.AppUserServices;
import com.example.securityServices.services.CommentServices;
import com.example.securityServices.services.PostServices;
import com.example.securityServices.services.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletSecurityElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
@Slf4j
@RestController @RequiredArgsConstructor @RequestMapping("/api")
public class AppUserResource {
    private final AppUserServices userServices;
    private final PostServices postServices;
    private final CommentServices commentServices;

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>>getUser(){
        return ResponseEntity.ok().body(userServices.getAppUsers());
    }

    //Added Code
    @GetMapping("/posts")
    public ResponseEntity<List<Post>>getPost(){
        return ResponseEntity.ok().body(postServices.getPosts());
    }
    //Added Code

    //Added Code
    @GetMapping("/comments")
    public ResponseEntity<List<Comment>>getComments(){
        return ResponseEntity.ok().body(commentServices.getComment());
    }
    //Added Code

    @PostMapping("/user/save")
    public ResponseEntity<AppUser>saveAppUser(@RequestBody AppUser appUser){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userServices.saveAppUser(appUser));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userServices.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form){
        userServices.addRoleToAppUser(form.getUserName(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
        {
            try
            {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                AppUser user = userServices.getAppUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUserName())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000 ))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            }
            catch(Exception exception)
            {
                log.error("Error logging in: {}", exception.getMessage());
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    //Added Code

    private final ServiceLayer serviceLayer;
    private RestTemplate restTemplate = new RestTemplate();

//    @GetMapping("/newusers")
//    public ResponseEntity<CommentObject> getData()
//    {
//        return new ResponseEntity<>(serviceLayer.consumeAPI(), HttpStatus.OK);
//    }

    @GetMapping("/getComments")
    public CommentObject[] getApiComments()
    {
        CommentObject[] commentObject = restTemplate.getForObject("https://gorest.co.in/public/v2/comments", CommentObject[].class);
        return commentObject;
    }

    @GetMapping("/getPosts")
    public PostObject[] getApiPosts()
    {
        PostObject[] postObject = restTemplate.getForObject("https://gorest.co.in/public/v2/posts", PostObject[].class);
        return postObject;
    }

    @GetMapping("/getUsers")
    public UserObject[] getApiUsers()
    {
        UserObject[] userObject = restTemplate.getForObject("https://gorest.co.in/public/v2/users", UserObject[].class);
        return userObject;
    }

    //Added Code
}

@Data
class RoleToUserForm{
    private String userName;
    private String roleName;
}
