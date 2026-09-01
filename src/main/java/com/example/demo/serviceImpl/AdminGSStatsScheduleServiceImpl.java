package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.GSStatsSchedule;
import com.example.demo.repository.AdminGSStatsScheduleRepo;
import com.example.demo.service.AdminGSStatsScheduleService;

@Service
public class AdminGSStatsScheduleServiceImpl implements AdminGSStatsScheduleService {

	@Autowired
	AdminGSStatsScheduleRepo gsScheduleRepo;

	@Override
	public GSStatsSchedule createGsSchedule(GSStatsSchedule schedule) {
		return gsScheduleRepo.save(schedule);
	}

	@Override
	public List<GSStatsSchedule> getAllGsScheduleList(Integer status) {
		Integer[] deletedStatus = { 2 };
		if (status < 2) {
			List<GSStatsSchedule> list = gsScheduleRepo.findByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
			return list;
		} else {
			List<GSStatsSchedule> list = gsScheduleRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
			return list;
		}
	}

	@Override
	public GSStatsSchedule getGsScheduleById(Integer id) {
		Optional<GSStatsSchedule> list = gsScheduleRepo.findById(id);
		return list.get();
	}

	@Override
	public GSStatsSchedule updateGsSchedule(GSStatsSchedule schedule) {
		GSStatsSchedule gsSchedule = null;
		Optional<GSStatsSchedule> s = gsScheduleRepo.findById(schedule.getId());
		if (s.isPresent()) {

			gsSchedule = s.get();

			if (StringUtils.isNotBlank(schedule.getDoc())) {
				gsSchedule.setDoc(schedule.getDoc());
			}

			if (schedule.getName() != null) {

				gsSchedule.setName(schedule.getName());
			}

			if (schedule.getDescription() != null) {

				gsSchedule.setDescription(schedule.getDescription());
			}

			if (schedule.getStatus() != null) {

				gsSchedule.setStatus(schedule.getStatus());
			}

			gsSchedule.setUpdatedAt(new Date());

		}
		GSStatsSchedule list = gsScheduleRepo.save(gsSchedule);
		return list;
	}

}
