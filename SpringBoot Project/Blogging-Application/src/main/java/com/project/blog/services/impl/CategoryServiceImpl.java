package com.project.blog.services.impl; 
// Defines the package for service implementation classes.

import java.util.List; 
// Importing List to handle collections of Category objects.
import java.util.stream.Collectors; 
// Importing Collectors to transform data using Java Streams.

import org.modelmapper.ModelMapper; 
// ModelMapper is used to map objects between DTOs and entities.
import org.springframework.beans.factory.annotation.Autowired; 
// Autowired annotation is used for dependency injection.
import org.springframework.stereotype.Service; 
// Marks this class as a Service component in the Spring application context.

import com.project.blog.entities.Category; 
// Importing the Category entity class.
import com.project.blog.exceptions.ResourceNotFoundException; 
// Importing custom exception for handling "resource not found" scenarios.
import com.project.blog.payloads.CategoryDto; 
// Importing the Data Transfer Object (DTO) for Category.
import com.project.blog.repositories.CategoryRepo; 
// Importing the repository interface for Category.
import com.project.blog.services.CategoryService; 
// Importing the CategoryService interface implemented by this class.

@Service 
// Marks this class as a service to be managed by Spring's IoC container.
public class CategoryServiceImpl implements CategoryService {

	@Autowired 
	// Automatically injects the dependency for CategoryRepo.
	private CategoryRepo categoryRepo;

	@Autowired 
	// Automatically injects the dependency for ModelMapper.
	private ModelMapper modelMapper;

	@Override 
	// Indicates that this method overrides a method in the CategoryService interface.
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// Maps the CategoryDto object to a Category entity using ModelMapper.
		Category cat = this.modelMapper.map(categoryDto, Category.class); 
		// Saves the mapped Category entity to the database.
		Category addedCat = this.categoryRepo.save(cat); 
		// Maps the saved Category entity back to a CategoryDto and returns it.
		return this.modelMapper.map(addedCat, CategoryDto.class); 
	}

	@Override 
	// Overrides the method to update an existing category.
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

		// Fetches the category by its ID or throws an exception if not found.
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id", categoryId));

		// Updates the title of the fetched category with the new value.
		cat.setCategoryTitle(categoryDto.getCategoryTitle()); 
		// Updates the description of the fetched category with the new value.
		cat.setCategoryDescription(categoryDto.getCategoryDescription()); 

		// Saves the updated category back to the database.
		Category updatedcat = this.categoryRepo.save(cat); 

		// Maps the updated Category entity back to a CategoryDto and returns it.
		return this.modelMapper.map(updatedcat, CategoryDto.class); 
	}

	@Override 
	// Overrides the method to delete a category by ID.
	public void deleteCategory(Integer categoryId) {

		// Fetches the category by its ID or throws an exception if not found.
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "category id", categoryId)); 
		// Deletes the fetched category from the database.
		this.categoryRepo.delete(cat); 
	}

	@Override 
	// Overrides the method to fetch a category by ID.
	public CategoryDto getCategory(Integer categoryId) {
		// Fetches the category by its ID or throws an exception if not found.
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId)); 

		// Maps the fetched Category entity to a CategoryDto and returns it.
		return this.modelMapper.map(cat, CategoryDto.class); 
	}

	@Override 
	// Overrides the method to fetch all categories.
	public List<CategoryDto> getCategories() {

		// Fetches all Category entities from the database.
		List<Category> categories = this.categoryRepo.findAll(); 
		// Maps each Category entity to a CategoryDto using Streams and Collectors.
		List<CategoryDto> catDtos = categories.stream()
				.map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList()); 

		// Returns the list of mapped CategoryDto objects.
		return catDtos; 
	}

}


