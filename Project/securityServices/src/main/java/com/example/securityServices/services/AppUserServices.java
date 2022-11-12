package com.example.securityServices.services;

import com.example.securityServices.domain.AppUser;
import com.example.securityServices.domain.Role;

import java.util.List;

public interface AppUserServices {
    AppUser saveAppUser(AppUser appUser);
    Role saveRole(Role role);
    void addRoleToAppUser(String appUserName, String roleName);
    AppUser getAppUser(String appUserName);
    List<AppUser>getAppUsers();
}
