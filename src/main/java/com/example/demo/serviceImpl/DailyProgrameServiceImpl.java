package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CommandantDailyProgramme;
import com.example.demo.payload.AddDailyProgramPayLoad;
import com.example.demo.payload.UpdateDailyProgramPayLoad;
import com.example.demo.repository.DailyProgrameRepo;
import com.example.demo.service.DailyProgrameService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.DateUtil;
import com.example.demo.util.FileWritting;

@Service
public class DailyProgrameServiceImpl implements DailyProgrameService {

	@Autowired
	DailyProgrameRepo programeRepo;

	@Override
	public Map<Object, Object> addDailyPrograme(AddDailyProgramPayLoad request, ServletRequest servletRequest) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			CommandantDailyProgramme dailyPrg = new CommandantDailyProgramme();

			dailyPrg.setScheduledDate(request.getScheduledDate());
			dailyPrg.setVenue(request.getVenue());
			dailyPrg.setTitle(request.getTitle());
			dailyPrg.setStartTime(request.getStartTime());
			dailyPrg.setEndTime(request.getEndTime());
			dailyPrg.setCreatedAt(DateUtil.convertTimeStampToDate(new Date().getTime()));

			CommandantDailyProgramme dailyPrgNew = programeRepo.save(dailyPrg);
			if (dailyPrgNew != null) {
				FileWritting.createLog((HttpServletRequest) servletRequest, dailyPrgNew.getId() + ",added,"
						+ "add Daily Programe," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.LIST, dailyPrgNew);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_ADDED_SUCCESSFULLY);
				return map;
			}

		} catch (Exception ex) {
			System.out.println(ex.toString());

			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

	@Override
	public Map<Object, Object> getAllDailyPrograme() {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			List<CommandantDailyProgramme> dailyPrgList = programeRepo.findAllByOrderByIdDesc();
			if (dailyPrgList.size() != 0) {
				map.put(ConstantMessage.LIST, dailyPrgList);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);

		}
		return map;

	}

	@Override
	public Map<Object, Object> getProgrameByDate(Long date) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			List<CommandantDailyProgramme> dailyPrgList = programeRepo.findTodaysProgram(date,
					DateUtil.getNextDayDate(date));
			if (dailyPrgList != null) {
				map.put(ConstantMessage.LIST, dailyPrgList);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);

		}
		return map;
	}

	@Override
	public Map<Object, Object> updateDailyPrograme(UpdateDailyProgramPayLoad request, ServletRequest servletRequest) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			CommandantDailyProgramme commandantDailyProgramme = programeRepo.findById(request.getId()).get();

			if (commandantDailyProgramme != null) {
				FileWritting.createLog((HttpServletRequest) servletRequest,
						commandantDailyProgramme.getId() + ",updated," + "updated Daily Programe,"
								+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				commandantDailyProgramme.setScheduledDate(request.getScheduledDate());
				commandantDailyProgramme.setVenue(request.getVenue());
				commandantDailyProgramme.setTitle(request.getTitle());
				commandantDailyProgramme.setStartTime(request.getStartTime());
				commandantDailyProgramme.setEndTime(request.getEndTime());

				CommandantDailyProgramme dailyPrgNew = programeRepo.save(commandantDailyProgramme);
				if (dailyPrgNew != null) {
					map.put(ConstantMessage.LIST, dailyPrgNew);
					map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
					map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_UPDATED_SUCCESSFULLY);
					return map;
				}

			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
			}
		} catch (Exception ex) {
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

	@Override
	public Map<Object, Object> getTodaysProgramme() {
		HashMap<Object, Object> map = new HashMap<>();
		try {

//			List<CommandantDailyProgramme> dailyPrgList = programeRepo.findTodaysProgram(DateUtil.todaysDate(),
//					DateUtil.nextDayDate());
			
			List<CommandantDailyProgramme> dailyPrgList = programeRepo.findTodaysProgramNew(DateUtil.todaysDate());
			if (dailyPrgList != null) {
				map.put(ConstantMessage.LIST, dailyPrgList);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);

		}
		return map;

	}

	@Override
	public Map<Object, Object> viewProgrammeById(Long id) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			CommandantDailyProgramme programme = programeRepo.findById(id).get();

			if (programme != null) {

				map.put(ConstantMessage.LIST, programme);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_UPDATED_SUCCESSFULLY);
				return map;

			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.INVALID_ID);
			}
		} catch (Exception ex) {
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

}
