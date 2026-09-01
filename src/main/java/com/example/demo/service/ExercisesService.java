package com.example.demo.service;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import com.example.demo.model.ExerciseType;
import com.example.demo.payload.AddExercises;
import com.example.demo.payload.GetDataOnlyById;
import com.example.demo.payload.UpdateExercise;

public interface ExercisesService {

	Map<Object, Object> addExercises(AddExercises request, ServletRequest servletRequest);

	// Map<Object, Object> getAllExercises(PaginationPayLoad request);

	Map<Object, Object> getAllExercises();

	Map<Object, Object> getDetailsByOnlyById(GetDataOnlyById request);

	Map<Object, Object> activeDeActiveExercise(Long id, int status, ServletRequest servletRequest);

	Map<Object, Object> updateExercise(UpdateExercise request, ServletRequest servletRequest);

	List<ExerciseType> getAllExerciseTypeList();

	ExerciseType createExerciseType(ExerciseType exerciseType);

	ExerciseType updateExerciseType(ExerciseType exerciseType);

	ExerciseType getExerciseTypeById(Long id);

	List<ExerciseType> getAllExerciseTypeListByStatus(Integer status);

	ExerciseType isAleadyExist(ExerciseType exerciseType);

	ExerciseType validateExerciseTypeExist(ExerciseType exerciseType);

}
