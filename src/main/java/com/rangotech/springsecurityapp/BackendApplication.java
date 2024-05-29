package com.rangotech.springsecurityapp;

import com.rangotech.springsecurityapp.persistence.entity.Role;
import com.rangotech.springsecurityapp.persistence.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	private final RoleRepository roleRepository;


	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		try {

			Role admin = new Role();
			admin.setId(101L);
			admin.setRole("ADMIN");

			Role user = new Role();
			user.setId(102L);
			user.setRole("USER");

			List<Role> roles = new ArrayList<>(List.of(admin, user));

			List<Role> savedRoles = roleRepository.saveAll(roles);

			savedRoles.forEach(System.out::println);
		}catch (Exception e){
			e.printStackTrace();
		}

	}
}
