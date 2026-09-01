package com.example.demo.payload;

import java.util.List;

import com.example.demo.model.Blog;

public class BlogPayload {

	private Integer totalRecords;
	private List<Blog> blogList;

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<Blog> getBlogList() {
		return blogList;
	}

	public void setBlogList(List<Blog> blogList) {
		this.blogList = blogList;
	}

}
