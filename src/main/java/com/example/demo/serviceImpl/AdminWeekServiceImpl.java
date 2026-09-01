package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Week;
import com.example.demo.myexception.MyException;
import com.example.demo.repository.AdminWeekRepo;
import com.example.demo.service.AdminWeekService;

@Service
public class AdminWeekServiceImpl implements AdminWeekService {
	
	private static Logger logger=LoggerFactory.getLogger(AdminWeekServiceImpl.class);

	@Autowired
	AdminWeekRepo weekRepo;

	@Override
	public Week createWeek(Week week) {
		return weekRepo.save(week);
	}

	@Override
	public List<Week> getAllScheduleList(){
		List<Week> list=null;
		try {
			logger.info("something went wrong in service");
		 list = weekRepo.findAllByOrderByIdAsc();
		 logger.info("list get successfully");
		 
		}
		catch(Exception ex) {
			ex.printStackTrace();
			logger.info(ex.getMessage());
		}
		return list;
	}

	@Override
	public Week getWeekById(Long id) {
		Optional<Week> list = weekRepo.findById(id);
		return list.get();
	}

	@Override
	public Week updateSchedule(Week week) {
		Week wk = null;
		Optional<Week> w = weekRepo.findById(week.getId());
		if (w.isPresent()) {

			wk = w.get();

			if (week.getName() != null) {

				wk.setName(wk.getName());
			}

			if (week.getStatus() != null) {

				wk.setStatus(wk.getStatus());
			}

			wk.setUpdated_at(new Date());

		}
		Week list = weekRepo.save(wk);
		return list;
	}

}
