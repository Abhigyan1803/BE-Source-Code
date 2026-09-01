package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ExerciseType;
import com.example.demo.model.Exercises;
import com.example.demo.model.RespDetails;
import com.example.demo.model.Term;
import com.example.demo.model.TermSeason;
import com.example.demo.payload.AddExercises;
import com.example.demo.payload.GetDataOnlyById;
import com.example.demo.payload.UpdateExercise;
import com.example.demo.repository.ExerciseTypeRepo;
import com.example.demo.repository.ExercisesRepo;
import com.example.demo.repository.RespDetailsRepo;
import com.example.demo.repository.TermRepo;
import com.example.demo.repository.TermSeasonRepo;
import com.example.demo.service.ExercisesService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;

@Service
public class ExercisesServiceImpl implements ExercisesService {

	@Autowired
	ExercisesRepo exercisesRepo;

	@Autowired
	RespDetailsRepo respDetailsRepo;

	@Autowired
	TermRepo termRepo;

	@Autowired
	TermSeasonRepo termSeasonRepo;

	@Autowired
	ExerciseTypeRepo exerciseTypeRepo;

	@Override
	public Map<Object, Object> addExercises(AddExercises request, ServletRequest servletRequest) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			Exercises exercise = new Exercises();

			exercise.setBbe(request.getBbe());
			exercise.setDsBriefing(request.getDsBriefing());
			exercise.setDuration(request.getDuration());
			exercise.setExercise(request.getExercise());
			exercise.setRecceTewt(request.getRecceTewt());
			exercise.setUrl(request.getUrl());
			
			RespDetails respDetails = respDetailsRepo.findById(request.getRespId()).get();

			exercise.setRespDetails(respDetails);
			exercise.setSmd(request.getSmd());
			Term term = termRepo.findById(request.getTermId()).get();

			exercise.setTerm(term);
			exercise.setVerbalOrders(request.getVerbalOrders());
			exercise.setStatus(request.getStatus());

			TermSeason termSeason = termSeasonRepo.findById(request.getSeasonTermId()).get();

			exercise.setTermSeason(termSeason);
			exercise.setYear(request.getYear());
			Exercises exercisesNew = exercisesRepo.save(exercise);

			if (exercisesNew != null) {
				FileWritting.createLog((HttpServletRequest) servletRequest, exercisesNew.getId() + ",added,"
						+ "addExercise," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());

				map.put(ConstantMessage.OBJECT_DETAILS, exercisesNew);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_ADDED_SUCCESSFULLY);
				return map;
			}

		} catch (Exception ex) {
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

	@Override
	public Map<Object, Object> getAllExercises() {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			// Pageable
			// pagedData=PageRequest.of(request.getpNumber(),request.getPageSize());
			Integer[] deletedStatus = { 2 };
			List<Exercises> exercisesList = exercisesRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
			if (!exercisesList.isEmpty()) {
				map.put(ConstantMessage.LIST, exercisesList);
				// map.put(ConstantMessage.LIST_SIZE,exercisesList.getTotalElements());
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
	public Map<Object, Object> getDetailsByOnlyById(GetDataOnlyById request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			Exercises exercise = exercisesRepo.findById(request.getId()).get();

			if (exercise != null) {
				map.put(ConstantMessage.OBJECT_DETAILS, exercise);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
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

	@Override
	public Map<Object, Object> activeDeActiveExercise(Long id, int status, ServletRequest servletRequest) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			Exercises exercise = exercisesRepo.findById(id).get();
			if (exercise != null) {
				exercise.setStatus(status);
				exercise = exercisesRepo.save(exercise);

				FileWritting.createLog((HttpServletRequest) servletRequest, exercise.getId() + ",status-update,"
						+ "activeDeActiveExercise," + ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());

				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.STATUS_UPDATED_SUCCESSFULLY);
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
	public Map<Object, Object> updateExercise(UpdateExercise request, ServletRequest servletRequest) {

		HashMap<Object, Object> map = new HashMap<>();
		try {
			Exercises exercises = exercisesRepo.findById(request.getId()).get();

			if (exercises != null) {

				exercises.setBbe(request.getBbe());
				exercises.setDsBriefing(request.getDsBriefing());
				exercises.setDuration(request.getDuration());
				exercises.setExercise(request.getExercise());
				exercises.setRecceTewt(request.getRecceTewt());
				exercises.setSmd(request.getSmd());
				exercises.setUrl(request.getUrl());
				Term term = termRepo.findById(request.getTermId()).get();
				exercises.setTerm(term);
				exercises.setVerbalOrders(request.getVerbalOrders());
				exercises.setStatus(request.getStatus());
				exercises.setUpdatedDate(new Date());
				RespDetails respDetails = respDetailsRepo.findById(request.getRespId()).get();
				exercises.setRespDetails(respDetails);
				TermSeason termSeason = termSeasonRepo.findById(request.getSeasonTermId()).get();

				exercises.setTermSeason(termSeason);
				exercises.setYear(request.getYear());

				Exercises exercisesNew = exercisesRepo.save(exercises);

				if (exercisesNew != null) {
					FileWritting.createLog((HttpServletRequest) servletRequest, exercisesNew.getId() + ",update,"
							+ "updateExercise," + ConstantMessage.RECORD_UPDATED_SUCCESSFULLY + "," + new Date());
					map.put(ConstantMessage.OBJECT_DETAILS, exercisesNew);
					map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
					map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_UPDATED_SUCCESSFULLY);
					return map;
				} else {
					map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
					map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
				}
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.INVALID_ID);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map;
	}

	@Override
	public List<ExerciseType> getAllExerciseTypeList() {
		List<ExerciseType> list = exerciseTypeRepo.findAllByStatus(1);
		return list;
	}

	@Override
	public ExerciseType createExerciseType(ExerciseType exerciseType) {
		ExerciseType result = exerciseTypeRepo.save(exerciseType);
		return result;
	}

	@Override
	public ExerciseType updateExerciseType(ExerciseType exerciseType) {
		ExerciseType exercise = null;
		Optional<ExerciseType> et = exerciseTypeRepo.findById(exerciseType.getId());
		if (et.isPresent()) {
			exercise = et.get();

			exercise.setUpdatedAt(new Date());
			if (exerciseType.getType() != null && exerciseType.getType().trim().length() != 0) {
				exercise.setType(exerciseType.getType());
			}

			if (exerciseType.getStatus() != null) {
				exercise.setStatus(exerciseType.getStatus());
			}

		}
		return exerciseTypeRepo.save(exercise);
	}

	@Override
	public ExerciseType getExerciseTypeById(Long id) {
		Optional<ExerciseType> et = exerciseTypeRepo.findById(id);
		return et.get();
	}

	@Override
	public List<ExerciseType> getAllExerciseTypeListByStatus(Integer status) {
		List<ExerciseType> list = null;
		if (status == 1) {
			list = exerciseTypeRepo.findByStatus(status);
		} else {
			list = exerciseTypeRepo.findAll();
		}
		return list;
	}

	@Override
	public ExerciseType isAleadyExist(ExerciseType exerciseType) {
		return exerciseTypeRepo.findByType(exerciseType.getType());
	}

	@Override
	public ExerciseType validateExerciseTypeExist(ExerciseType exerciseType) {
		ExerciseType et = exerciseTypeRepo.findByType(exerciseType.getType());
		if (et != null && et.getId() != exerciseType.getId()) {
			return et;
		}
		return null;
	}
}
