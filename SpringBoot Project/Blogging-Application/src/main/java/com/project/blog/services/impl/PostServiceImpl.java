package com.project.blog.services.impl;
// Defines the package for the implementation of blog services.

import java.util.Date;
// Provides the `Date` class for managing date and time.
import java.util.List;
// Provides the `List` interface for working with collections.
import java.util.stream.Collectors;
// Provides utilities for transforming collections using streams.

import org.modelmapper.ModelMapper;
// Used for mapping objects from one type to another.
import org.springframework.beans.factory.annotation.Autowired;
// Enables dependency injection for Spring components.
import org.springframework.data.domain.Page;
// Represents a page of results from a paginated query.
import org.springframework.data.domain.PageRequest;
// Provides a way to define pagination details (e.g., page number and size).
import org.springframework.data.domain.Pageable;
// Represents pagination and sorting information.
import org.springframework.data.domain.Sort;
// Represents sorting order (ascending or descending).
import org.springframework.stereotype.Service;
// Marks this class as a Spring-managed service component.

import com.project.blog.entities.Category;
// Represents the `Category` entity class.
import com.project.blog.entities.Post;
// Represents the `Post` entity class.
import com.project.blog.entities.User;
// Represents the `User` entity class.
import com.project.blog.exceptions.ResourceNotFoundException;
// Custom exception for handling resource not found scenarios.
import com.project.blog.payloads.PostDto;
// Represents the Data Transfer Object (DTO) for a post.
import com.project.blog.payloads.PostResponse;
// Represents a paginated response for posts.
import com.project.blog.repositories.CategoryRepo;
// Provides repository operations for the `Category` entity.
import com.project.blog.repositories.PostRepo;
// Provides repository operations for the `Post` entity.
import com.project.blog.repositories.UserRepo;
// Provides repository operations for the `User` entity.
import com.project.blog.services.PostService;
// Interface defining the post-related operations.

@Service
// Marks this class as a Spring service to manage blog post operations.
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo; 
    // Injects the repository for performing CRUD operations on posts.

    @Autowired
    private ModelMapper modelMapper; 
    // Injects the ModelMapper to map objects between DTOs and entities.

    @Autowired
    private UserRepo userRepo; 
    // Injects the repository for managing user-related database operations.

    @Autowired
    private CategoryRepo categoryRepo; 
    // Injects the repository for managing category-related database operations.

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        // Fetch the user by userId, or throw an exception if not found.
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User ", "User id", userId));

        // Fetch the category by categoryId, or throw an exception if not found.
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id ", categoryId));

        // Map the PostDto to a Post entity.
        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png"); // Set a default image name for the post.
        post.setAddedDate(new Date()); // Set the current date as the added date.
        post.setUser(user); // Associate the user with the post.
        post.setCategory(category); // Associate the category with the post.

        // Save the post in the database.
        Post newPost = this.postRepo.save(post);

        // Map the saved Post entity back to a PostDto and return it.
        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        // Fetch the post by postId, or throw an exception if not found.
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post ", "post id", postId));

        // Fetch the category associated with the updated post.
        Category category = this.categoryRepo.findById(postDto.getCategory().getCategoryId()).get();

        // Update the post's fields with values from the DTO.
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setCategory(category);

        // Save the updated post in the database.
        Post updatedPost = this.postRepo.save(post);

        // Map the updated Post entity to a PostDto and return it.
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {

        // Fetch the post by postId, or throw an exception if not found.
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post ", "post id", postId));

        // Delete the post from the database.
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        // Determine the sorting order based on the sort direction (ascending/descending).
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // Create a Pageable object to define pagination and sorting details.
        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        // Retrieve a paginated list of posts from the database.
        Page<Post> pagePost = this.postRepo.findAll(p);

        // Extract the list of Post entities from the Page object.
        List<Post> allPosts = pagePost.getContent();

        // Map each Post entity to a PostDto using streams.
        List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        // Create a PostResponse object to encapsulate the paginated response.
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos); // Set the list of PostDto objects.
        postResponse.setPageNumber(pagePost.getNumber()); // Set the current page number.
        postResponse.setPageSize(pagePost.getSize()); // Set the page size.
        postResponse.setTotalElements(pagePost.getTotalElements()); // Set the total number of posts.
        postResponse.setTotalPages(pagePost.getTotalPages()); // Set the total number of pages.
        postResponse.setLastPage(pagePost.isLast()); // Indicate if the current page is the last.

        return postResponse; // Return the paginated response.
    }

    @Override
    public PostDto getPostById(Integer postId) {
        // Fetch the post by postId, or throw an exception if not found.
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        // Map the Post entity to a PostDto and return it.
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {

        // Fetch the category by categoryId, or throw an exception if not found.
        Category cat = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

        // Retrieve all posts associated with the category.
        List<Post> posts = this.postRepo.findByCategory(cat);

        // Map each Post entity to a PostDto using streams.
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        return postDtos; // Return the list of PostDto objects.
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {

        // Fetch the user by userId, or throw an exception if not found.
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User ", "userId ", userId));

        // Retrieve all posts created by the user.
        List<Post> posts = this.postRepo.findByUser(user);

        // Map each Post entity to a PostDto using streams.
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        return postDtos; // Return the list of PostDto objects.
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {

        // Search for posts by title using the provided keyword.
        List<Post> posts = this.postRepo.searchByTitle("%" + keyword + "%");

        // Map each Post entity to a PostDto using streams.
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        return postDtos; // Return the list of PostDto objects.
    }

}
