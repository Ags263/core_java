package com.project.blog.services;

// Importing necessary classes and interfaces.
import java.util.List;
import com.project.blog.payloads.CategoryDto;

// Interface defining the contract for category-related operations.
public interface CategoryService {

	// Method to create a new category.
	// Takes a CategoryDto object as input and returns the created CategoryDto.
	CategoryDto createCategory(CategoryDto categoryDto);

	// Method to update an existing category.
	// Takes a CategoryDto object and the category ID as input and returns the updated CategoryDto.
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	// Method to delete a category by its ID.
	// Takes the category ID as input and performs the delete operation.
	void deleteCategory(Integer categoryId);

	// Method to fetch a single category by its ID.
	// Takes the category ID as input and returns the corresponding CategoryDto.
	CategoryDto getCategory(Integer categoryId);

	// Method to fetch all categories.
	// Returns a list of CategoryDto objects representing all categories.
	List<CategoryDto> getCategories();

}
