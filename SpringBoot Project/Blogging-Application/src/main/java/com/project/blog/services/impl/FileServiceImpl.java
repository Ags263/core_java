package com.project.blog.services.impl;
// Defines the package for service implementation classes.

import java.io.File;
// Provides the `File` class to handle file operations.
import java.io.FileInputStream;
// Used to read bytes from a file as an input stream.
import java.io.FileNotFoundException;
// Exception thrown when a file cannot be found.
import java.io.IOException;
// General exception class for I/O operations.
import java.io.InputStream;
// Provides an abstraction for reading data from a file.
import java.nio.file.Files;
// Utility class to perform file-related operations like copying files.
import java.nio.file.Paths;
// Used to handle file paths in a flexible and OS-independent way.
import java.util.UUID;
// Provides methods for generating unique identifiers.

import org.springframework.stereotype.Service;
// Marks this class as a service to be managed by Spring's IoC container.
import org.springframework.web.multipart.MultipartFile;
// Represents a file uploaded through an HTTP request.

import com.project.blog.services.FileService;
// Imports the FileService interface implemented by this class.

@Service
// Marks this class as a Spring service, making it a candidate for dependency injection.
public class FileServiceImpl implements FileService {

	@Override
	// Implements the method to handle image uploads.
	public String uploadImage(String path, MultipartFile file) throws IOException {

		// Retrieve the original filename of the uploaded file (e.g., "abc.png").
		String name = file.getOriginalFilename();

		// Generate a random unique ID to ensure the file name is unique.
		String randomID = UUID.randomUUID().toString();
		// Combine the random ID with the file's extension (e.g., "1234abc-xyz.png").
		String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));

		// Build the full path where the file will be saved (e.g., "/uploads/1234abc.png").
		String filePath = path + File.separator + fileName1;

		// Create the directory if it does not already exist.
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir(); // Creates the directory.
		}

		// Copy the file from the input stream to the destination path.
		Files.copy(file.getInputStream(), Paths.get(filePath));

		// Return the new file name (including the random ID) for reference.
		return fileName1;
	}

	@Override
	// Implements the method to retrieve a file as an InputStream.
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {

		// Build the full path to the file using the directory path and file name.
		String fullPath = path + File.separator + fileName;

		// Open the file as an InputStream. Throws FileNotFoundException if the file doesn't exist.
		InputStream is = new FileInputStream(fullPath);

		// Return the InputStream to the caller (e.g., for file download).
		return is;
	}

}
