package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.MediaCategory;

@Repository
public interface MediaCategoryRepo extends JpaRepository<MediaCategory,Long> {

	List<MediaCategory> findAllByOrderByIdAsc();

	
	
}
