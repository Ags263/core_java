package com.project.blog.controllers; // Specifies the package for this controller.

import java.util.List; // Importing List for handling collections of data.
import java.util.Map; // Importing Map (though not used in this class).

import javax.validation.Valid; // Used to validate request body objects.

import org.springframework.beans.factory.annotation.Autowired; // Used for dependency injection.
import org.springframework.http.HttpStatus; // Enum for HTTP response status codes.
import org.springframework.http.ResponseEntity; // Used to construct HTTP responses.
import org.springframework.security.access.prepost.PreAuthorize; // Used to implement method-level security.
import org.springframework.web.bind.annotation.CrossOrigin; // Enables Cross-Origin Resource Sharing (CORS).
import org.springframework.web.bind.annotation.DeleteMapping; // Maps HTTP DELETE requests.
import org.springframework.web.bind.annotation.GetMapping; // Maps HTTP GET requests.
import org.springframework.web.bind.annotation.PathVariable; // Used to bind URL path variables to method parameters.
import org.springframework.web.bind.annotation.PostMapping; // Maps HTTP POST requests.
import org.springframework.web.bind.annotation.PutMapping; // Maps HTTP PUT requests.
import org.springframework.web.bind.annotation.RequestBody; // Binds request body data to method parameters.
import org.springframework.web.bind.annotation.RequestMapping; // Maps the base URL for this controller.
import org.springframework.web.bind.annotation.RestController; // Marks this class as a REST controller.

import com.project.blog.payloads.ApiResponse; // Custom payload for standardized API responses.
import com.project.blog.payloads.UserDto; // Data transfer object for user data.
import com.project.blog.services.UserService; // Service to handle user-related business logic.

@RestController // Marks this class as a REST controller that handles HTTP requests.
@RequestMapping("/api/v1/users") // Base URL for all endpoints in this controller.
public class UserController {

	@Autowired // Injecting the UserService dependency.
	private UserService userService;

	// POST - Endpoint to create a new user.
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		// Calls the service layer to create a new user.
		UserDto createUserDto = this.userService.createUser(userDto);
		// Returns the created user with HTTP status 201 (Created).
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	// PUT - Endpoint to update an existing user.
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid) {
		// Calls the service layer to update the user with the given ID.
		UserDto updatedUser = this.userService.updateUser(userDto, uid);
		// Returns the updated user with HTTP status 200 (OK).
		return ResponseEntity.ok(updatedUser);
	}

	// DELETE - Endpoint to delete a user. Only accessible by ADMIN users.
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid) {
		// Calls the service layer to delete the user with the given ID.
		this.userService.deleteUser(uid);
		// Returns a response message confirming successful deletion with HTTP status 200 (OK).
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);
	}

	// GET - Endpoint to retrieve a list of all users.
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		// Calls the service layer to fetch all users.
		return ResponseEntity.ok(this.userService.getAllUsers());
	}

	// GET - Endpoint to retrieve details of a single user by their ID.
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
		// Calls the service layer to fetch the user with the given ID.
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}

}
