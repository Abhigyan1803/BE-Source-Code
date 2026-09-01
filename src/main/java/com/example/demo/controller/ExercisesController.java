package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ExerciseType;
import com.example.demo.payload.AddExercises;
import com.example.demo.payload.GetDataOnlyById;
import com.example.demo.payload.UpdateExercise;
import com.example.demo.service.ExercisesService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/exercisesController")
@CrossOrigin
public class ExercisesController {

	@Autowired
	ExercisesService exercisesService;

	@PostMapping("/addExercise")
	public Map<Object, Object> addExercise(@RequestBody AddExercises request, ServletRequest servletRequest) {
		return exercisesService.addExercises(request, servletRequest);
	}

	// @ApiOperation(value="Get all daily programes")
	@PostMapping("/getAllExercises")
	public Map<Object, Object> getAllExercises() {
		return exercisesService.getAllExercises();
	}

	@PostMapping("/getExerciseById")
	public Map<Object, Object> getExerciseById(GetDataOnlyById request) {
		return exercisesService.getDetailsByOnlyById(request);
	}

	@PostMapping("/activeDeActiveExercise")
	public Map<Object, Object> activeDeactiveExercise(Long id, int status, ServletRequest servletRequest) {
		return exercisesService.activeDeActiveExercise(id, status, servletRequest);
	}

	@PostMapping("/updateExercise")
	public Map<Object, Object> updateExercise(@RequestBody UpdateExercise request, ServletRequest servletRequest) {
		return exercisesService.updateExercise(request, servletRequest);
	}

	@GetMapping(value = "/getAllExerciseTypeList")
	public ResponseEntity<?> exerciseTypeList() {
		List<ExerciseType> list = exercisesService.getAllExerciseTypeList();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getAllExerciseTypeListByStatus")
	public ResponseEntity<?> exerciseTypeListByStatus(@RequestParam Integer status) {
		List<ExerciseType> list = exercisesService.getAllExerciseTypeListByStatus(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PostMapping(value = "/add_exercise_type")
	public ResponseEntity<?> addExerciseType(@RequestBody ExerciseType exerciseType, ServletRequest request) {
		ExerciseType exerciseTypeExist = exercisesService.isAleadyExist(exerciseType);
		if (exerciseTypeExist == null) {
			ExerciseType response = exercisesService.createExerciseType(exerciseType);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.EXERCISE_TYPE_ADDED, HttpStatus.OK, response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.EXERCISE_TYPE_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@PostMapping(value = "/update_exercise_type")
	public ResponseEntity<?> updateExerciseType(@RequestBody ExerciseType exerciseType, ServletRequest request) {
		ExerciseType exerciseTypeExist = exercisesService.validateExerciseTypeExist(exerciseType);
		if (exerciseTypeExist == null) {
			ExerciseType response = exercisesService.updateExerciseType(exerciseType);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.EXERCISE_TYPE_UPDATED, HttpStatus.OK, response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.EXERCISE_TYPE_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/get_exercise_type_by_id")
	public ResponseEntity<?> getExerciseTypeById(@RequestParam Long id) {
		ExerciseType response = exercisesService.getExerciseTypeById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}
}
