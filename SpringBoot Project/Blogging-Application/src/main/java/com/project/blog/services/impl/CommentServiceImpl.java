package com.project.blog.services.impl;
// Defines the package for service implementation classes.

import org.modelmapper.ModelMapper;
// ModelMapper is used to map objects between DTOs and entities.
import org.springframework.beans.factory.annotation.Autowired;
// Autowired annotation is used for dependency injection.
import org.springframework.stereotype.Service;
// Marks this class as a Service component in the Spring application context.

import com.project.blog.entities.Comment;
// Importing the Comment entity class.
import com.project.blog.entities.Post;
// Importing the Post entity class.
import com.project.blog.exceptions.ResourceNotFoundException;
// Importing the custom exception for handling "resource not found" scenarios.
import com.project.blog.payloads.CommentDto;
// Importing the Data Transfer Object (DTO) for comments.
import com.project.blog.repositories.CommentRepo;
// Importing the repository interface for Comment.
import com.project.blog.repositories.PostRepo;
// Importing the repository interface for Post.
import com.project.blog.services.CommentService;
// Importing the CommentService interface implemented by this class.

@Service
// Marks this class as a service to be managed by Spring's IoC container.
public class CommentServiceImpl implements CommentService {

	@Autowired
	// Automatically injects the dependency for PostRepo.
	private PostRepo postRepo;

	@Autowired
	// Automatically injects the dependency for CommentRepo.
	private CommentRepo commentRepo;

	@Autowired
	// Automatically injects the dependency for ModelMapper.
	private ModelMapper modelMapper;

	@Override
	// Overrides the method to create a comment for a specific post.
	public CommentDto createComment(CommentDto commentDto, Integer postId) {

		// Fetches the Post entity by its ID or throws an exception if not found.
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id ", postId));

		// Maps the CommentDto object to a Comment entity using ModelMapper.
		Comment comment = this.modelMapper.map(commentDto, Comment.class);

		// Associates the fetched Post entity with the new Comment.
		comment.setPost(post);

		// Saves the Comment entity to the database.
		Comment savedComment = this.commentRepo.save(comment);

		// Maps the saved Comment entity back to a CommentDto and returns it.
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	// Overrides the method to delete a comment by ID.
	public void deleteComment(Integer commentId) {

		// Fetches the Comment entity by its ID or throws an exception if not found.
		Comment com = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));
		// Deletes the fetched Comment entity from the database.
		this.commentRepo.delete(com);
	}
}
