package com.project.blog.services;

// Importing the necessary classes.
import com.project.blog.payloads.CommentDto;

// Interface defining the contract for comment-related operations.
public interface CommentService {

	// Method to create a new comment for a specific post.
	// Takes a CommentDto object and the post ID as input and returns the created CommentDto.
	CommentDto createComment(CommentDto commentDto, Integer postId);

	// Method to delete a comment by its ID.
	// Takes the comment ID as input and performs the delete operation.
	void deleteComment(Integer commentId);

}
