package com.sh.blog.services.implmentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sh.blog.services.FileService;

@Service
public class FileServiceImplementation implements FileService
{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException 
	{
		String name = file.getOriginalFilename();
		String randomID =UUID.randomUUID().toString();
		String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));
		String filePath = path + "/" + fileName1;
		File file2 = new File(path);
		if (!file2.exists()) 
		{
			file2.mkdir();
		}
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String fileName) 
			throws FileNotFoundException 
	{
		String fullpath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullpath);
		return is;
	}

}
