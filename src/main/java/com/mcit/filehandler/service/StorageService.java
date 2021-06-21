package com.mcit.filehandler.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mcit.filehandler.exception.StorageException;

@Service
public class StorageService {

	@Value("${upload.path}")
	private String path;
	
	//upload file
	public void uploadFile(MultipartFile file) {
		// file is empty
		if(file.isEmpty()) {
			throw new StorageException("Failed to upload a file, file is empty!");
		}
		// file upload logic
		try {
			String fileName= file.getOriginalFilename();
			InputStream ins = file.getInputStream();
			
			Files.copy(ins, Paths.get(path+fileName), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new StorageException("Failed to upload a file !");
		}		
	}
	
	public String getFile(String fileName) {
		return path+fileName;
	}
	
}
