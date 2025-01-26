package com.project.blog.exceptions; 
// Declares the package containing exception-handling-related classes.

import java.util.HashMap; // Importing HashMap to store field error details.
import java.util.Map; // Importing Map to define a structure for error messages.

import org.springframework.http.HttpStatus; // Defines HTTP status codes.
import org.springframework.http.ResponseEntity; // Represents the HTTP response, including headers, body, and status.
import org.springframework.validation.FieldError; // Represents a field-specific error in validation.
import org.springframework.web.bind.MethodArgumentNotValidException; 
// Handles exceptions thrown when method arguments fail validation.
import org.springframework.web.bind.annotation.ExceptionHandler; 
// Annotation used to handle exceptions globally in the application.
import org.springframework.web.bind.annotation.RestControllerAdvice; 
// Provides centralized exception handling for controllers with `@RestController`.

import com.project.blog.payloads.ApiResponse; 
// Importing the `ApiResponse` class to format responses in a standardized way.

@RestControllerAdvice 
// Indicates that this class provides global exception handling for REST controllers.
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class) 
	// Handles exceptions of type `ResourceNotFoundException`.
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		// Retrieves the error message from the `ResourceNotFoundException`.
		String message = ex.getMessage(); 
		// Creates an `ApiResponse` object with the error message and a `false` success status.
		ApiResponse apiResponse = new ApiResponse(message, false); 
		// Returns the response with a `404 Not Found` HTTP status.
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND); 
	}

	@ExceptionHandler(MethodArgumentNotValidException.class) 
	// Handles exceptions when method arguments fail validation.
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
		// Initializes a `Map` to store validation errors (field name as key and error message as value).
		Map<String, String> resp = new HashMap<>(); 
		// Iterates over all validation errors in the exception object.
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			// Retrieves the name of the field that caused the validation error.
			String fieldName = ((FieldError) error).getField(); 
			// Retrieves the default error message for the field.
			String message = error.getDefaultMessage(); 
			// Adds the field name and error message to the response map.
			resp.put(fieldName, message); 
		});

		// Returns the validation errors with a `400 Bad Request` HTTP status.
		return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST); 
	}

	@ExceptionHandler(ApiException.class) 
	// Handles custom exceptions of type `ApiException`.
	public ResponseEntity<ApiResponse> handleApiException(ApiException ex) {
		// Retrieves the error message from the `ApiException`.
		String message = ex.getMessage(); 
		// Creates an `ApiResponse` object with the error message and a `true` success status.
		ApiResponse apiResponse = new ApiResponse(message, true); 
		// Returns the response with a `400 Bad Request` HTTP status.
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST); 
	}
}
