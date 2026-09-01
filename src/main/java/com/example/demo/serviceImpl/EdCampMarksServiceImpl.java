package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EdCampMarks;
import com.example.demo.repository.EdCampMarksRepository;
import com.example.demo.service.EdCampMarksService;

@Service
public class EdCampMarksServiceImpl implements EdCampMarksService {

	@Autowired
	private EdCampMarksRepository repo;

	@Override
	public EdCampMarks addEdCampMarks(EdCampMarks edCampMarks) {
		// TODO Auto-generated method stub
		return repo.save(edCampMarks);
	}

	@Override
	public List<EdCampMarks> getByServiceId(String serviceId) {
		// TODO Auto-generated method stub
		List<EdCampMarks> result = repo.findByServiceId(serviceId);
		return result;
	}

	@Override
	public EdCampMarks getByServiceIdAndTermId(String serviceId, Long termId) {
		// TODO Auto-generated method stub
		return repo.findByServiceIdAndTermId(serviceId, termId);
	}

	@Override
	public EdCampMarks updateEdCampMarks(EdCampMarks edCampMarks) {
		// TODO Auto-generated method stub
		EdCampMarks marks = null;
		if (edCampMarks != null && edCampMarks.getId() != null && edCampMarks.getId() != 0) {
			Optional<EdCampMarks> ed = repo.findById(edCampMarks.getId());
			if (ed.isPresent()) {
				marks = ed.get();

				if (edCampMarks.getCampPerformance() != null) {
					marks.setCampPerformance(edCampMarks.getCampPerformance());
				}
				if (edCampMarks.getSpotTest() != null) {
					marks.setSpotTest(edCampMarks.getSpotTest());
				}
				if (edCampMarks.getCoyPerformance() != null) {
					marks.setCoyPerformance(edCampMarks.getCoyPerformance());
				}
				if (edCampMarks.getTotalCampMarks() != null) {
					marks.setTotalCampMarks(edCampMarks.getTotalCampMarks());
				}
				if (edCampMarks.getSignature() != null) {
					marks.setSignature(edCampMarks.getSignature());
				}
				marks.setUpdatedAt(new Date());
			}
			marks = repo.save(marks);
		}
		return marks;
	}

}
