package com.example.securityServices;

import com.example.securityServices.domain.AppUser;
import com.example.securityServices.domain.Role;
import com.example.securityServices.services.AppUserServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityServicesApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(AppUserServices appUserServices) {
		return args -> {
			appUserServices.saveRole(new Role(null,"ROLE_USER"));
			appUserServices.saveRole(new Role(null,"ROLE_MANAGER"));
			appUserServices.saveRole(new Role(null,"ROLE_ADMIN"));
			appUserServices.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

			appUserServices.saveAppUser(new AppUser(null, "Syed Ali Asghar", "asghar", "1234", new ArrayList<>()));
			appUserServices.saveAppUser(new AppUser(null, "Syed Waqar Haider", "haider", "1234", new ArrayList<>()));
			appUserServices.saveAppUser(new AppUser(null, "Syed Ali Qasim", "qasim", "1234", new ArrayList<>()));
			appUserServices.saveAppUser(new AppUser(null, "Syed Ashiq Hussain", "hussain", "1234", new ArrayList<>()));

			appUserServices.addRoleToAppUser("asghar","ROLE_ADMIN");
			appUserServices.addRoleToAppUser("haider","ROLE_USER");
			appUserServices.addRoleToAppUser("qasim","ROLE_MANAGER");
			appUserServices.addRoleToAppUser("hussain","ROLE_SUPER_ADMIN");
			appUserServices.addRoleToAppUser("hussain","ROLE_ADMIN");
			appUserServices.addRoleToAppUser("hussain","ROLE_MANAGER");

		};
	}

}

