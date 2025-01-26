package com.project.blog.services;

// Importing necessary classes for file handling.
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

// Interface defining the contract for file-related operations.
public interface FileService {

	// Method to upload an image to a specific path.
	// Takes the target path and the file (MultipartFile) as input and returns the name of the uploaded file.
	// Throws IOException in case of any input/output operation issues.
	String uploadImage(String path, MultipartFile file) throws IOException;

	// Method to fetch a file as an InputStream for reading.
	// Takes the path and file name as input and returns the InputStream of the file.
	// Throws FileNotFoundException if the file is not found at the specified path.
	InputStream getResource(String path, String fileName) throws FileNotFoundException;

}
