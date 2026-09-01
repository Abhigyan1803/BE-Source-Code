package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CommandantRecommendedReadingList;
import com.example.demo.repository.CommandantRecommendedReadingListRepo;
import com.example.demo.service.CommandantRecommendedReadingListService;

@Service
public class CommandantRecommendedReadingListServiceImpl implements CommandantRecommendedReadingListService {

	@Autowired
	CommandantRecommendedReadingListRepo readingListRepo;

	@Override
	public CommandantRecommendedReadingList addRecommendedBook(CommandantRecommendedReadingList recommendedBook) {
		return readingListRepo.save(recommendedBook);
	}

	@Override
	public List<CommandantRecommendedReadingList> getAllRecommendedBook(Integer status) {
		Integer[] deletedStatus = { 2 };
		if (status < 2) {
			List<CommandantRecommendedReadingList> list = readingListRepo
					.findByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
			return list;
		} else {
			List<CommandantRecommendedReadingList> list = readingListRepo
					.findAllByStatusNotInOrderByIdDesc(deletedStatus);
			return list;
		}
	}

	@Override
	public CommandantRecommendedReadingList getRecommendedBook(Long id) {
		Optional<CommandantRecommendedReadingList> list = readingListRepo.findById(id);
		return list.get();
	}

	@Override
	public CommandantRecommendedReadingList updateRecommendedBook(CommandantRecommendedReadingList recommendedBook) {
		CommandantRecommendedReadingList rl = null;
		Optional<CommandantRecommendedReadingList> s = readingListRepo.findById(recommendedBook.getId());
		if (s.isPresent()) {
			rl = s.get();

			if (recommendedBook.getAuthorName() != null) {
				rl.setAuthorName(recommendedBook.getAuthorName());
			}
			if (recommendedBook.getBookGenre() != null) {
				rl.setBookGenre(recommendedBook.getBookGenre());
			}
			if (recommendedBook.getBookName() != null) {
				rl.setBookName(recommendedBook.getBookName());
			}
			if (recommendedBook.getDescription() != null) {
				rl.setDescription(recommendedBook.getDescription());
			}
			if (recommendedBook.getStatus() != null) {
				rl.setStatus(recommendedBook.getStatus());
			}
			rl.setUpdatedAt(new Date());
		}
		CommandantRecommendedReadingList list = readingListRepo.save(rl);
		return list;
	}
}
