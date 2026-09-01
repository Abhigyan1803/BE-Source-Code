package com.example.demo.service;

import java.util.List;

import com.example.demo.model.CommandantRecommendedReadingList;

public interface CommandantRecommendedReadingListService {

	CommandantRecommendedReadingList addRecommendedBook(CommandantRecommendedReadingList recommendedBook);

	List<CommandantRecommendedReadingList> getAllRecommendedBook(Integer status);

	CommandantRecommendedReadingList getRecommendedBook(Long id);

	CommandantRecommendedReadingList updateRecommendedBook(CommandantRecommendedReadingList recommendedBook);

}
