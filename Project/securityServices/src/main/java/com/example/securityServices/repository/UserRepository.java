package com.example.securityServices.repository;

import com.example.securityServices.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
AppUser findByUserName(String userName);
}
