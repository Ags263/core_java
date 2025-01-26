package com.project.blog.controllers; // Package declaration for the controllers of the blog project.

import java.security.Principal; // Importing Principal to access details of the currently logged-in user.
import java.util.Optional; // Importing Optional for handling optional values.

import javax.validation.Valid; // Importing @Valid for validating incoming request bodies.

import org.modelmapper.ModelMapper; // Importing ModelMapper for converting entities to DTOs.
import org.springframework.beans.factory.annotation.Autowired; // Importing Autowired for dependency injection.
import org.springframework.http.HttpStatus; // Importing HttpStatus for defining HTTP response statuses.
import org.springframework.http.ResponseEntity; // Importing ResponseEntity for building HTTP responses.
import org.springframework.security.authentication.AuthenticationManager; // Importing AuthenticationManager for managing authentication.
import org.springframework.security.authentication.BadCredentialsException; // Importing exception for handling invalid credentials.
import org.springframework.security.authentication.DisabledException; // Importing exception for handling disabled accounts.
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; // Token for authentication.
import org.springframework.security.core.userdetails.UserDetails; // Importing UserDetails for handling user information.
import org.springframework.security.core.userdetails.UserDetailsService; // Importing service to load user-specific data.
import org.springframework.ui.ModelMap; // Importing ModelMap (though unused in this code).
import org.springframework.web.bind.annotation.CrossOrigin; // For enabling cross-origin requests (not used here).
import org.springframework.web.bind.annotation.GetMapping; // Importing annotation for mapping GET requests.
import org.springframework.web.bind.annotation.PostMapping; // Importing annotation for mapping POST requests.
import org.springframework.web.bind.annotation.RequestBody; // To bind request body data to method parameters.
import org.springframework.web.bind.annotation.RequestMapping; // To map web requests to the controller.
import org.springframework.web.bind.annotation.RestController; // Marks this class as a REST controller.

import com.project.blog.entities.User; // Importing User entity.
import com.project.blog.exceptions.ApiException; // Importing custom exception for API errors.
import com.project.blog.payloads.JwtAuthRequest; // Importing payload for JWT authentication request.
import com.project.blog.payloads.JwtAuthResponse; // Importing payload for JWT authentication response.
import com.project.blog.payloads.UserDto; // Importing DTO for User.
import com.project.blog.repositories.UserRepo; // Importing repository for User entity.
import com.project.blog.security.JwtTokenHelper; // Utility class for generating JWT tokens.
import com.project.blog.services.UserService; // Service layer for user-related operations.

@RestController // Marking this class as a REST controller.
@RequestMapping("/api/v1/auth/") // Base URL for all endpoints in this controller.
public class AuthController {

	@Autowired // Dependency injection for JwtTokenHelper.
	private JwtTokenHelper jwtTokenHelper;

	@Autowired // Dependency injection for UserDetailsService.
	private UserDetailsService userDetailsService;

	@Autowired // Dependency injection for AuthenticationManager.
	private AuthenticationManager authenticationManager;

	@Autowired // Dependency injection for UserService.
	private UserService userService;

	@PostMapping("/login") // Mapping POST requests to "/login".
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		// Authenticating user credentials.
		this.authenticate(request.getUsername(), request.getPassword());
		// Loading user details after successful authentication.
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		// Generating JWT token for authenticated user.
		String token = this.jwtTokenHelper.generateToken(userDetails);

		// Creating response object with token and user details.
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		response.setUser(this.mapper.map((User) userDetails, UserDto.class));
		// Returning response with status OK (200).
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	// Method to authenticate user credentials.
	private void authenticate(String username, String password) throws Exception {

		// Creating an authentication token with username and password.
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		try {
			// Authenticating the token using the authentication manager.
			this.authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			// Handling invalid credentials exception.
			System.out.println("Invalid Details !!");
			throw new ApiException("Invalid username or password !!");
		}
	}

	@PostMapping("/register") // Mapping POST requests to "/register".
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
		// Registering a new user and returning the created user details.
		UserDto registeredUser = this.userService.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
	}

	@Autowired // Dependency injection for UserRepo.
	private UserRepo userRepo;
	@Autowired // Dependency injection for ModelMapper.
	private ModelMapper mapper;

	@GetMapping("/current-user/") // Mapping GET requests to "/current-user/".
	public ResponseEntity<UserDto> getUser(Principal principal) {
		// Fetching the currently logged-in user's data using their email (from Principal).
		User user = this.userRepo.findByEmail(principal.getName()).get();
		// Mapping User entity to UserDto and returning with status OK (200).
		return new ResponseEntity<UserDto>(this.mapper.map(user, UserDto.class), HttpStatus.OK);
	}
}
