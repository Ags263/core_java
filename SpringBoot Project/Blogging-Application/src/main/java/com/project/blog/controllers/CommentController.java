package com.project.blog.controllers; // Package declaration for the controllers in the blog project.

import org.springframework.beans.factory.annotation.Autowired; // Importing @Autowired for dependency injection.
import org.springframework.http.HttpStatus; // Importing HttpStatus to define HTTP response statuses.
import org.springframework.http.ResponseEntity; // Importing ResponseEntity to build HTTP responses.
import org.springframework.web.bind.annotation.DeleteMapping; // Annotation for mapping DELETE requests.
import org.springframework.web.bind.annotation.PathVariable; // Annotation for accessing variables in the URL path.
import org.springframework.web.bind.annotation.PostMapping; // Annotation for mapping POST requests.
import org.springframework.web.bind.annotation.RequestBody; // Annotation to bind request body data to method parameters.
import org.springframework.web.bind.annotation.RequestMapping; // Annotation to define the base URL for this controller.
import org.springframework.web.bind.annotation.RestController; // Marks this class as a REST controller.

import com.project.blog.payloads.ApiResponse; // Importing custom response payload for API responses.
import com.project.blog.payloads.CommentDto; // Importing data transfer object (DTO) for Comment.
import com.project.blog.services.CommentService; // Importing service layer for comment-related operations.

@RestController // Marking this class as a REST controller.
@RequestMapping("/api/v1/") // Base URL for all endpoints in this controller.
public class CommentController {

	@Autowired // Injecting the CommentService bean into this controller.
	private CommentService commentService;

	// Create a new comment for a specific post.
	@PostMapping("/post/{postId}/comments") // Mapping POST requests to "/post/{postId}/comments".
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId) {
		// Using the service layer to create a new comment for the specified post ID.
		CommentDto createComment = this.commentService.createComment(comment, postId);
		// Returning the created comment along with HTTP status 201 (Created).
		return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);
	}

	// Delete an existing comment by its ID.
	@DeleteMapping("/comments/{commentId}") // Mapping DELETE requests to "/comments/{commentId}".
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
		// Using the service layer to delete the comment by its ID.
		this.commentService.deleteComment(commentId);
		// Returning a custom response message indicating successful deletion with HTTP status 200 (OK).
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully !!", true), HttpStatus.OK);
	}

}
