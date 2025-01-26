package com.project.blog;

// Importing necessary classes and packages.
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.blog.config.AppConstants;
import com.project.blog.entities.Role;
import com.project.blog.repositories.RoleRepo;

// Main application class annotated with @SpringBootApplication to enable Spring Boot functionalities.
@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {

	// Autowiring the PasswordEncoder bean to use for encoding passwords.
	@Autowired
	private PasswordEncoder passwordEncoder;

	// Autowiring the RoleRepo for role-related database operations.
	@Autowired
	private RoleRepo roleRepo;

	// Main method to bootstrap the application.
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

	// Defining a bean for ModelMapper to perform object mapping (DTO <-> Entity).
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	// The run method is executed after the application starts, as this class implements CommandLineRunner.
	@Override
	public void run(String... args) throws Exception {

		// Printing an encoded password for demonstration purposes.
		System.out.println(this.passwordEncoder.encode("xyz"));

		try {

		

			// Creating a role object for "ROLE_NORMAL".
			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL_USER); // Setting the ID for the normal user role.
			role1.setName("ROLE_NORMAL"); // Setting the name for the normal user role.

			// Adding the roles to a list.
			List<Role> roles = List.of( role1);

			// Saving the roles into the database using RoleRepo.
			List<Role> result = this.roleRepo.saveAll(roles);

			// Printing the names of all saved roles for verification.
			result.forEach(r -> {
				System.out.println(r.getName());
			});

		} catch (Exception e) {
			// Catching any exceptions that occur during the role creation process.
			e.printStackTrace(); // Printing the stack trace for debugging.
		}
	}
}
