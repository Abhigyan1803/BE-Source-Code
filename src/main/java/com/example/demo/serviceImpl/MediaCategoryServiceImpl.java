package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.MediaCategory;
import com.example.demo.repository.MediaCategoryRepo;
import com.example.demo.service.MediaCategoryService;

@Service
public class MediaCategoryServiceImpl implements MediaCategoryService {

	@Autowired
	MediaCategoryRepo mediaCategoryRepo;
	
	@Override
	public List<MediaCategory> getAllMediaCategories() {
		return mediaCategoryRepo.findAllByOrderByIdAsc();
	}

}
