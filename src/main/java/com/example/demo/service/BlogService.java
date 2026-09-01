package com.example.demo.service;

import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.data.domain.Pageable;

import com.example.demo.model.Blog;
import com.example.demo.payload.BlogPayload;

public interface BlogService {

	Blog addBlog(Blog blog, ServletRequest request);

	BlogPayload getBlogList(Integer battalionId, Integer status, Pageable pageable);

	Blog getBlogById(Long id);

	Blog updateBlog(Blog blog, ServletRequest request);

	List<Blog> getBlogListByCategory(String cat);

}
