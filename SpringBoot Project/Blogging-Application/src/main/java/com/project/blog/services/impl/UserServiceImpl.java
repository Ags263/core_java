package com.project.blog.services.impl;

// Importing necessary packages for the implementation.
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.blog.config.AppConstants;
import com.project.blog.entities.*;
import com.project.blog.exceptions.*;
import com.project.blog.payloads.UserDto;
import com.project.blog.repositories.*;
import com.project.blog.services.UserService;

// Annotating the class as a Spring service to define business logic.
@Service
public class UserServiceImpl implements UserService {

	// Autowiring the UserRepo to interact with the User database table.
	@Autowired
	private UserRepo userRepo;

	// Autowiring ModelMapper for object mapping between DTO and Entity.
	@Autowired
	private ModelMapper modelMapper;

	// Autowiring PasswordEncoder for encoding user passwords.
	@Autowired
	private PasswordEncoder passwordEncoder;

	// Autowiring RoleRepo to fetch roles from the database.
	@Autowired
	private RoleRepo roleRepo;

	// Implementation of createUser method for saving a new user.
	@Override
	public UserDto createUser(UserDto userDto) {
		// Converting UserDto to User entity.
		User user = this.dtoToUser(userDto);
		// Saving the User entity to the database.
		User savedUser = this.userRepo.save(user);
		// Returning the saved User entity as a DTO.
		return this.userToDto(savedUser);
	}

	// Implementation of updateUser method for updating an existing user.
	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		// Fetching the User by userId or throwing an exception if not found.
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

		// Updating the User's fields with new data from the DTO.
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		// Saving the updated User entity back to the database.
		User updatedUser = this.userRepo.save(user);
		// Mapping the updated User entity to a DTO and returning it.
		UserDto userDto1 = this.userToDto(updatedUser);
		return userDto1;
	}

	// Implementation of getUserById method to fetch a user by their ID.
	@Override
	public UserDto getUserById(Integer userId) {

		// Fetching the User by userId or throwing an exception if not found.
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

		// Mapping the fetched User entity to a DTO and returning it.
		return this.userToDto(user);
	}

	// Implementation of getAllUsers method to fetch all users.
	@Override
	public List<UserDto> getAllUsers() {

		// Fetching all User entities from the database.
		List<User> users = this.userRepo.findAll();
		// Mapping the list of User entities to a list of UserDto and returning it.
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

		return userDtos;
	}

	// Implementation of deleteUser method to delete a user by their ID.
	@Override
	public void deleteUser(Integer userId) {
		// Fetching the User by userId or throwing an exception if not found.
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		// Deleting the fetched User entity from the database.
		this.userRepo.delete(user);
	}

	// Helper method to convert UserDto to User entity.
	public User dtoToUser(UserDto userDto) {
		// Using ModelMapper to map the DTO to the User entity.
		User user = this.modelMapper.map(userDto, User.class);

		// The below lines are commented because ModelMapper already handles these mappings.
		// user.setId(userDto.getId());
		// user.setName(userDto.getName());
		// user.setEmail(userDto.getEmail());
		// user.setAbout(userDto.getAbout());
		// user.setPassword(userDto.getPassword());
		return user;
	}

	// Helper method to convert User entity to UserDto.
	public UserDto userToDto(User user) {
		// Using ModelMapper to map the User entity to the DTO.
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

	// Implementation of registerNewUser method for registering a new user with default roles.
	@Override
	public UserDto registerNewUser(UserDto userDto) {

		// Mapping the UserDto to a User entity.
		User user = this.modelMapper.map(userDto, User.class);

		// Encoding the user's password before saving it to the database.
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		// Fetching the default role for a normal user.
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

		// Adding the default role to the user's roles.
		user.getRoles().add(role);

		// Saving the new User entity to the database.
		User newUser = this.userRepo.save(user);

		// Mapping the saved User entity back to a UserDto and returning it.
		return this.modelMapper.map(newUser, UserDto.class);
	}

}
