package com.example.demo.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public class FileUploader {

	public static String uploadProfileImage(MultipartFile mulfile, String path) {

		if (!mulfile.isEmpty()) {
		
			Long timestamp = System.currentTimeMillis();

			String filename = mulfile.getOriginalFilename();
			String extension = FilenameUtils.getExtension(filename);

			filename = timestamp.toString().concat("." + extension);

		try {
//				byte[] bytes = mulfile.getBytes();
//
//				BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(path + filename, true));
//				buffStream.write(bytes);
//				buffStream.close();
//				return filename;
//
			File filePath = new File(path + filename);
			InputStream inputStream = mulfile.getInputStream();
			FileUtils.copyInputStreamToFile(inputStream, filePath);
			return filename;
		} 
			
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error==>>" + e.getStackTrace());
			}
		}

		return "";

	}

	public static Pageable paginationData(int pageNumber, int pageSize) {
		Pageable paging = PageRequest.of(pageNumber, pageSize);
		return paging;
	}

}
