package com.example.securityServices.services;

import com.example.securityServices.domain.AppUser;
import com.example.securityServices.domain.Comment;
import com.example.securityServices.domain.Post;
import com.example.securityServices.domain.Role;
import com.example.securityServices.repository.CommentRepository;
import com.example.securityServices.repository.PostRepository;
import com.example.securityServices.repository.RoleRepository;
import com.example.securityServices.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class AppUserServicesImplementation implements AppUserServices, UserDetailsService {
     private final UserRepository userRepository;
     private final RoleRepository roleRepository;

     private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String appUserName) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUserName(appUserName);
        if(appUser == null){
          log.error("User not found in database");
          throw new UsernameNotFoundException("User not found in database");
        }
        else
        {
            log.info("User found in database: {}", appUserName);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(appUser.getUserName(), appUser.getPassword(), authorities);
    }



    @Override
    public AppUser saveAppUser(AppUser appUser) {
        log.info("Saving new user {} to database", appUser.getName());
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return userRepository.save(appUser);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to database", role.getName());
        return roleRepository.save(role);
    }


    @Override
    public void addRoleToAppUser(String appUserName, String roleName) {
        log.info("Adding role {} to user {}", roleName, appUserName);
        AppUser appUser = userRepository.findByUserName(appUserName);
        Role role = roleRepository.findByName(roleName);
        appUser.getRoles().add(role);

    }

    @Override
    public AppUser getAppUser(String appUserName)
    {
        log.info("Fetching user {}", appUserName);
        return userRepository.findByUserName(appUserName);
    }

    @Override
    public List<AppUser> getAppUsers()
    {
        log.info("Fetching all users");
        return userRepository.findAll();
    }






}
