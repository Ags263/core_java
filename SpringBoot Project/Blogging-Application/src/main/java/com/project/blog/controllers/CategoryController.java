package com.project.blog.controllers; // Package declaration for the controllers of the blog project.

import java.util.List; // Importing List to handle collections of data.

import javax.validation.Valid; // Importing @Valid for validating incoming request bodies.

import org.springframework.beans.factory.annotation.Autowired; // Importing @Autowired for dependency injection.
import org.springframework.http.HttpStatus; // Importing HttpStatus to define HTTP response statuses.
import org.springframework.http.ResponseEntity; // Importing ResponseEntity to build HTTP responses.
import org.springframework.web.bind.annotation.CrossOrigin; // For enabling cross-origin requests (not used here but imported).
import org.springframework.web.bind.annotation.DeleteMapping; // Annotation for mapping DELETE requests.
import org.springframework.web.bind.annotation.GetMapping; // Annotation for mapping GET requests.
import org.springframework.web.bind.annotation.PathVariable; // Annotation for accessing variables in the URL path.
import org.springframework.web.bind.annotation.PostMapping; // Annotation for mapping POST requests.
import org.springframework.web.bind.annotation.PutMapping; // Annotation for mapping PUT requests.
import org.springframework.web.bind.annotation.RequestBody; // Annotation to bind request body data to method parameters.
import org.springframework.web.bind.annotation.RequestMapping; // Annotation to define base URL for all controller endpoints.
import org.springframework.web.bind.annotation.RestController; // Marks this class as a REST controller.

import com.project.blog.payloads.ApiResponse; // Importing custom response payload for API responses.
import com.project.blog.payloads.CategoryDto; // Importing data transfer object (DTO) for Category.
import com.project.blog.services.CategoryService; // Importing service layer for category-related operations.

@RestController // Marking this class as a REST controller.
@RequestMapping("/api/categories") // Base URL for all endpoints in this controller.
public class CategoryController {

	@Autowired // Injecting the CategoryService bean into this controller.
	private CategoryService categoryService;

	// Create a new category
	@PostMapping("/") // Mapping POST requests to "/api/categories/".
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto cateogDto) {
		// Using the service layer to create a new category.
		CategoryDto createCategory = this.categoryService.createCategory(cateogDto);
		// Returning the created category along with HTTP status 201 (Created).
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
	}

	// Update an existing category
	@PutMapping("/{catId}") // Mapping PUT requests to "/api/categories/{catId}".
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer catId) {
		// Using the service layer to update the category based on its ID.
		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, catId);
		// Returning the updated category with HTTP status 200 (OK).
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	}

	// Delete a category
	@DeleteMapping("/{catId}") // Mapping DELETE requests to "/api/categories/{catId}".
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId) {
		// Using the service layer to delete the category by its ID.
		this.categoryService.deleteCategory(catId);
		// Returning a custom response message indicating successful deletion with HTTP status 200 (OK).
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully !!", true),
				HttpStatus.OK);
	}

	// Get a single category by ID
	@GetMapping("/{catId}") // Mapping GET requests to "/api/categories/{catId}".
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId) {
		// Using the service layer to fetch the category details by its ID.
		CategoryDto categoryDto = this.categoryService.getCategory(catId);
		// Returning the category details with HTTP status 200 (OK).
		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
	}

	// Get all categories
	@GetMapping("/") // Mapping GET requests to "/api/categories/".
	public ResponseEntity<List<CategoryDto>> getCategories() {
		// Using the service layer to fetch all categories.
		List<CategoryDto> categories = this.categoryService.getCategories();
		// Returning the list of all categories with HTTP status 200 (OK).
		return ResponseEntity.ok(categories);
	}
}
