package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Schedule;
import com.example.demo.repository.AdminScheduleRepo;
import com.example.demo.service.AdminScheduleService;

@Service
public class AdminScheduleServiceImpl implements AdminScheduleService {

	@Autowired
	AdminScheduleRepo scheduleRepo;

	@Override
	public Schedule createSchedule(Schedule schedule) {
		return scheduleRepo.save(schedule);
	}

//	@Override
//	public List<Schedule> getAllScheduleList() {
//		List<Schedule> list = scheduleRepo.findAllByStatus(ConstantVar.ONE);
//		return list;
//	}

//	@Override
//	public Page<Schedule> getAllScheduleList(Pageable paginationData) {
////		Pageable paging = PageRequest.of(pageNo, pageSize);
////		Page<Schedule> list = scheduleRepo.findAllByStatusOrderByIdDesc(ConstantVar.ONE, paginationData);
//		Page<Schedule> list = scheduleRepo.findAllByOrderByIdDesc(paginationData);
//		return list;
//	}

	@Override
	public List<Schedule> getAllScheduleList() {
//		Pageable paging = PageRequest.of(pageNo, pageSize);
//		Page<Schedule> list = scheduleRepo.findAllByStatusOrderByIdDesc(ConstantVar.ONE, paginationData);
//		List<Schedule> list = scheduleRepo.findAllByOrderByIdDesc();
//		return list;
		return null;
	}

	@Override
	public Schedule getScheduleById(Integer id) {
		Optional<Schedule> list = scheduleRepo.findById(id);
		return list.get();
	}

	@Override
	public Schedule updateSchedule(Schedule schedule) {
//		Schedule schdl = null;
//		Optional<Schedule> sch = scheduleRepo.findById(schedule.getId());
//		if (sch.isPresent()) {
//
//			schdl = sch.get();
//
//			if (schedule.getBattalian() != null) {
//
//				schdl.setBattalian(schedule.getBattalian());
//			}
//
//			if (schedule.getSessionTerm() != null) {
//
//				schdl.setSessionTerm(schedule.getSessionTerm());
//			}
//
//			if (schedule.getWeek() != null) {
//
//				schdl.setWeek(schedule.getWeek());
//			}
//
//			if (schedule.getTerm() != null) {
//
//				schdl.setTerm(schedule.getTerm());
//			}
//
//			if (schedule.getStartTime() != null) {
//
//				schdl.setStartTime(schedule.getStartTime());
//			}
//
//			if (schedule.getEndTime() != null) {
//
//				schdl.setEndTime(schedule.getEndTime());
//			}
//
//			if (schedule.getPeriod() != null) {
//
//				schdl.setPeriod(schedule.getPeriod());
//			}
//
//			if (schedule.getSubject() != null) {
//
//				schdl.setSubject(schedule.getSubject());
//			}
//
//			if (schedule.getType() != null) {
//
//				schdl.setType(schedule.getType());
//			}
//
//			if (schedule.getLession() != null) {
//
//				schdl.setLession(schedule.getLession());
//			}
//
//			if (schedule.getInstruction() != null) {
//
//				schdl.setInstruction(schedule.getInstruction());
//			}
//
//			if (schedule.getPlace() != null) {
//
//				schdl.setPlace(schedule.getPlace());
//			}
//
//			if (schedule.getYear() != null) {
//
//				schdl.setYear(schedule.getYear());
//			}
//
//			if (schedule.getStatus() != null) {
//
//				schdl.setStatus(schedule.getStatus());
//			}
//			if (schedule.getDate() != null) {
//
//				schdl.setDate(schedule.getDate());
//			}
//
//			schdl.setUpdated_at(new Date());
//
//		}
//		Schedule list = scheduleRepo.save(schdl);
//		return list;
		return null;

	}

}
