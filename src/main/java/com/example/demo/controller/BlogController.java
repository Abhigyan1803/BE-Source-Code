package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Blog;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.BlogPayload;
import com.example.demo.service.BlogService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/blog")
public class BlogController {

	@Autowired
	BlogService blogService;

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@PostMapping(value = "/add-blog")
	public ResponseEntity<?> addBlog(Blog blog,
			@RequestParam(value = "approvedDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date approvedDate,
			@RequestParam(value = "blgImage", required = false) MultipartFile[] file,
			@RequestParam(value = "doc", required = false) MultipartFile doc, ServletRequest request) {
		if (file != null) {
			String uploaded_doc = StringUtils.EMPTY;
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				blog.setBlogImage(url + uploaded_doc);
			}
		}
		if (doc != null) {
			String uploaded_doc = StringUtils.EMPTY;
			uploaded_doc = FileUploader.uploadProfileImage(doc, UploadDir);
			blog.setDocs(url + uploaded_doc);
		}
		Blog response = blogService.addBlog(blog, request);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "blog," + ConstantMessage.BLOG_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.BLOG_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-blog-list") // battalionId 13-03-2023
	public ResponseEntity<?> getBlogList(@RequestParam (value = "battalionId", required = false) Integer battalionId, @RequestParam Integer status, @RequestParam Integer pageNo, Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		BlogPayload response = blogService.getBlogList(battalionId, status, pageable);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-blog")
	public ResponseEntity<?> getBlogByID(@RequestParam Long id) {
		Blog list = blogService.getBlogById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-blog")
	public ResponseEntity<?> updateBlog(Blog blog,
			@RequestParam(value = "approvedDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date approvedDate,
			@RequestParam(value = "blgImage", required = false) MultipartFile[] file,
			@RequestParam(value = "doc", required = false) MultipartFile doc, ServletRequest request)
			throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		if (file != null) {
			long length = file.length;
			System.err.println("file length " + length);
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				blog.setBlogImage(url + uploaded_doc);
			}
		}

		if (doc != null) {
			uploaded_doc = FileUploader.uploadProfileImage(doc, UploadDir);
			blog.setDocs(url + uploaded_doc);
		}

		Blog response = blogService.updateBlog(blog, request);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",updated," + "blog," + ConstantMessage.BLOG_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.BLOG_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-blog-list-by-category")
	public ResponseEntity<?> getBlogListByCategory(@RequestParam String cat) {
		List<Blog> list = blogService.getBlogListByCategory(cat);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}
}
