package com.project.blog.controllers; // Defines the package for the controller class.

import java.io.IOException; // Used to handle input-output exceptions.
import java.io.InputStream; // Used to read data from streams.
import java.util.List; // Importing List for handling collections of data.

import javax.servlet.http.HttpServletResponse; // Used to modify HTTP response.

import org.hibernate.engine.jdbc.StreamUtils; // Utility to copy streams (used for file download).
import org.springframework.beans.factory.annotation.Autowired; // Annotation for dependency injection.
import org.springframework.beans.factory.annotation.Value; // Annotation to inject property values from configuration.
import org.springframework.http.HttpStatus; // Enum for HTTP response status codes.
import org.springframework.http.MediaType; // Represents the media type for content negotiation.
import org.springframework.http.ResponseEntity; // Used to construct HTTP responses.
import org.springframework.web.bind.annotation.CrossOrigin; // Enables Cross-Origin Resource Sharing (CORS).
import org.springframework.web.bind.annotation.DeleteMapping; // Maps HTTP DELETE requests.
import org.springframework.web.bind.annotation.GetMapping; // Maps HTTP GET requests.
import org.springframework.web.bind.annotation.PathVariable; // Used to bind URL path variables to method parameters.
import org.springframework.web.bind.annotation.PostMapping; // Maps HTTP POST requests.
import org.springframework.web.bind.annotation.PutMapping; // Maps HTTP PUT requests.
import org.springframework.web.bind.annotation.RequestBody; // Binds request body data to method parameters.
import org.springframework.web.bind.annotation.RequestMapping; // Maps the base URL for this controller.
import org.springframework.web.bind.annotation.RequestParam; // Binds query parameters to method parameters.
import org.springframework.web.bind.annotation.RestController; // Marks this class as a REST controller.
import org.springframework.web.multipart.MultipartFile; // Represents a file uploaded with a multipart request.

import com.project.blog.config.AppConstants; // Importing application constants.
import com.project.blog.entities.Post; // Importing the Post entity class.
import com.project.blog.payloads.ApiResponse; // Custom payload for API responses.
import com.project.blog.payloads.PostDto; // Data transfer object for posts.
import com.project.blog.payloads.PostResponse; // Response structure for paginated posts.
import com.project.blog.services.FileService; // Service to handle file operations.
import com.project.blog.services.PostService; // Service to handle post-related operations.

@RestController // Indicates that this class is a REST controller.
@RequestMapping("/api/v1/") // Base URL for all endpoints in this controller.
public class PostController {

	@Autowired // Injecting PostService dependency.
	private PostService postService;

	@Autowired // Injecting FileService dependency.
	private FileService fileService;

	@Value("${project.image}") // Injecting the image path from application properties.
	private String path;

	// Endpoint to create a new post.
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		// Calls service to create a post for the given user and category.
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		// Returns the created post with HTTP status 201 (Created).
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}

	// Endpoint to get all posts by a specific user.
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
		// Calls service to fetch posts by user ID.
		List<PostDto> posts = this.postService.getPostsByUser(userId);
		// Returns the list of posts with HTTP status 200 (OK).
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// Endpoint to get all posts under a specific category.
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
		// Calls service to fetch posts by category ID.
		List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
		// Returns the list of posts with HTTP status 200 (OK).
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// Endpoint to get all posts with pagination and sorting.
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
		// Calls service to fetch paginated and sorted posts.
		PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		// Returns the paginated response with HTTP status 200 (OK).
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	// Endpoint to get details of a specific post by its ID.
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		// Calls service to fetch a post by its ID.
		PostDto postDto = this.postService.getPostById(postId);
		// Returns the post details with HTTP status 200 (OK).
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}

	// Endpoint to delete a post by its ID.
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		// Calls service to delete a post by its ID.
		this.postService.deletePost(postId);
		// Returns a custom response message confirming successful deletion.
		return new ApiResponse("Post is successfully deleted !!", true);
	}

	// Endpoint to update a post.
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		// Calls service to update the post.
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		// Returns the updated post details with HTTP status 200 (OK).
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}

	// Endpoint to search posts by keywords in the title.
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords) {
		// Calls service to search posts by title keywords.
		List<PostDto> result = this.postService.searchPosts(keywords);
		// Returns the search results with HTTP status 200 (OK).
		return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
	}


}
