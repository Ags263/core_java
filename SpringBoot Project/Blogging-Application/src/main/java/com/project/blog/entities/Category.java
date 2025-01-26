package com.project.blog.entities; // Package declaration for the entities in the blog project.

import java.util.ArrayList; // Importing ArrayList for managing lists of posts.
import java.util.List; // Importing List interface for type safety and abstraction.

import javax.persistence.CascadeType; // Specifies cascading operations for related entities.
import javax.persistence.Column; // Used to customize column attributes in the database table.
import javax.persistence.Entity; // Marks this class as a JPA entity.
import javax.persistence.FetchType; // Defines fetch strategies for related entities (EAGER or LAZY).
import javax.persistence.GeneratedValue; // Specifies how primary key values should be generated.
import javax.persistence.GenerationType; // Enumerates different primary key generation strategies.
import javax.persistence.Id; // Marks a field as the primary key of the entity.
import javax.persistence.OneToMany; // Defines a one-to-many relationship between entities.
import javax.persistence.Table; // Specifies the database table to which this entity maps.

import lombok.Getter; // Lombok annotation to auto-generate getter methods.
import lombok.NoArgsConstructor; // Lombok annotation to generate a no-argument constructor.
import lombok.Setter; // Lombok annotation to auto-generate setter methods.

@Entity // Indicates that this class is a JPA entity mapped to a database table.
@Table(name="categories") // Maps this entity to the "categories" table in the database.
@NoArgsConstructor // Generates a no-argument constructor for this class.
@Getter // Lombok annotation to auto-generate getters for all fields.
@Setter // Lombok annotation to auto-generate setters for all fields.
public class Category {

	@Id // Marks this field as the primary key of the entity.
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	// Specifies that the primary key will be generated automatically using the IDENTITY strategy 
	// (commonly used with auto-increment fields in databases).
	private Integer categoryId; // Unique identifier for each category.

	@Column(name="title", length = 100, nullable = false) 
	// Maps this field to the "title" column in the database, with a maximum length of 100 characters. 
	// The field cannot be null.
	private String categoryTitle; // Stores the title of the category.

	@Column(name="description") 
	// Maps this field to the "description" column in the database.
	private String categoryDescription; // Stores the description of the category.

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
	// Defines a one-to-many relationship with the `Post` entity. 
	// - `mappedBy = "category"`: Indicates that the "category" field in the `Post` entity owns the relationship.
	// - `cascade = CascadeType.ALL`: Ensures that all operations (e.g., persist, remove) performed on a `Category`
	//   are cascaded to its related `Post` entities.
	// - `fetch = FetchType.LAZY`: Related `Post` entities are loaded lazily, meaning they are only loaded when accessed.
	private List<Post> posts = new ArrayList<>(); 
	// Initializes an empty list to hold related posts. Each category can have multiple posts.

}
