package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CadetWeaponTrainingMainResult;
import com.example.demo.model.CadetWeaponTrainingResult;
import com.example.demo.model.SpotTestWtt;
import com.example.demo.model.Weapon;
import com.example.demo.model.WeaponTrainingResult;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.WeaponTrainingResultFilterPayload;
import com.example.demo.payload.WeaponTrainingResultPayload;
import com.example.demo.service.WeaponService;
import com.example.demo.service.WeaponTrainingResultService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/training-result")
public class WeaponTrainingResultController {
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	private WeaponService weaponService;

	@Autowired
	WeaponTrainingResultService weaponTrainingResultService;

	@PostMapping(value = "/save-result")
	public ResponseEntity<?> addResult(@RequestBody List<CadetWeaponTrainingResult> result, ServletRequest request)
			throws MyException {

		List<CadetWeaponTrainingResult> response = weaponTrainingResultService.createResult(result);
		FileWritting.createLog((HttpServletRequest) request, response.get(0).getServiceId() + ",add,"
				+ "training-result," + ConstantMessage.MARKS_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.MARKS_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-cadet-result")
	public ResponseEntity<?> getResult(@RequestParam String serviceId, @RequestParam Long termId) {
		List<CadetWeaponTrainingResult> response = weaponTrainingResultService.getCadetResult(serviceId, termId);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PatchMapping(value = "update-result")
	public ResponseEntity<?> updateResult(@RequestBody List<CadetWeaponTrainingResult> result, ServletRequest request)
			throws MyException {
		List<CadetWeaponTrainingResult> response = weaponTrainingResultService.updateResult(result);
		FileWritting.createLog((HttpServletRequest) request, response.get(0).getServiceId() + ",update,"
				+ "training-result," + ConstantMessage.MARKS_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.MARKS_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping(value = "/save-main-result")
	public ResponseEntity<?> saveMainResult(@RequestBody List<CadetWeaponTrainingMainResult> mainResult,
			ServletRequest request) throws MyException {

		List<CadetWeaponTrainingMainResult> response = weaponTrainingResultService.createMainResult(mainResult);
		FileWritting.createLog((HttpServletRequest) request, response.get(0).getServiceId() + ",add-main-result,"
				+ "training-result," + ConstantMessage.MARKS_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.MARKS_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-main-result")
	public ResponseEntity<?> getMainResult(@RequestParam String serviceId, @RequestParam Long termId) {
		List<CadetWeaponTrainingMainResult> response = weaponTrainingResultService.getCadetMainResult(serviceId,
				termId);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PatchMapping(value = "update-main-result")
	public ResponseEntity<?> updateMainResult(@RequestBody CadetWeaponTrainingMainResult result, ServletRequest request)
			throws MyException {
		CadetWeaponTrainingMainResult response = weaponTrainingResultService.updateMainResult(result);
		FileWritting.createLog((HttpServletRequest) request, response.getServiceId() + ",update-main-result,"
				+ "training-result," + ConstantMessage.MARKS_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.MARKS_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping(value = "/save-cadet-weapon-training-result")
	public ResponseEntity<?> addCadetWTResult(@RequestBody WeaponTrainingResult result, ServletRequest request)
			throws MyException {

		WeaponTrainingResult response = weaponTrainingResultService.createCadetWTResult(result);
		FileWritting.createLog((HttpServletRequest) request, response.getServiceId() + ",add," + "training-result,"
				+ ConstantMessage.MARKS_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.MARKS_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@PostMapping(value = "/add_spotTest_and_wtt_marks")
	public ResponseEntity<?> addStAndWttMarks(@RequestBody SpotTestWtt spotTestWtt) {

		if (spotTestWtt.getTermId() != null) {
			SpotTestWtt response = weaponTrainingResultService.getByTermId(spotTestWtt.getTermId());
			if (response == null) {
				response = weaponTrainingResultService.addSpotTestAndWttMarks(spotTestWtt);
				return new ResponseEntity<>(
						new ResponseMessage(ConstantMessage.SPOT_TEST_WTT_MARKS_ADDED, HttpStatus.OK, response),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>(
						new ResponseMessage(ConstantMessage.SPOT_TEST_AND_WTT_ALREADY_EXIST, HttpStatus.OK, response),
						HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.FAILED_TO_ADD_SPOT_TEST_AND_WTT_MARKS, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_spotTest_and_wtt_marks_by_term")
	public ResponseEntity<?> getStAndWttMarksByTerm(@RequestParam Long termId) {
		SpotTestWtt response = weaponTrainingResultService.getByTermId(termId);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_spotTest_and_wtt_marks_by_Status")
	public ResponseEntity<?> getAllStAndWttMarksByStatus(@RequestParam Integer status) {
		List<SpotTestWtt> response = weaponTrainingResultService.getAllSpotTestAndWttMarksByStatus(status);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@PutMapping(value = "/update_spotTest_and_wtt_marks")
	public ResponseEntity<?> updateStAndWttMarks(@RequestBody SpotTestWtt spotTestWtt) {
		SpotTestWtt response = weaponTrainingResultService.updateSpotTestAndWttMarks(spotTestWtt);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.SPOT_TEST_WTT_MARKS_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-cadet-weapon-main-result")
	public ResponseEntity<?> getCadetWeaponMainResult(@RequestParam String serviceId, @RequestParam Long termId) {
		WeaponTrainingResult response = weaponTrainingResultService.getCadetWeaponMainResult(serviceId, termId);

		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			Set<Weapon> list = weaponService.getWeaponByTerm(termId, 1);
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ADD, HttpStatus.OK, list), HttpStatus.OK);
		}
	}

	@PutMapping(value = "/update-cadet-weapon-main-result")
	public ResponseEntity<?> updateCadetWeaponResult(@RequestBody WeaponTrainingResult weaponTrainingResult)
			throws MyException {
		WeaponTrainingResult weaponTrainingRslt = weaponTrainingResultService
				.updateCadetWeaponResult(weaponTrainingResult);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.CADET_WEAPON_UPDATED, HttpStatus.OK, weaponTrainingRslt),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-cadet-weapon-main-result_by_serviceId")
	public ResponseEntity<?> getCadetWeaponMainResultByServiceId(@RequestParam String serviceId) {
		List<WeaponTrainingResult> response = weaponTrainingResultService
				.getCadetWeaponMainResultByServiceId(serviceId);

		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/get_all_cadet_weapon_training_result_by_termId_battalion_company")
	public ResponseEntity<?> getCadetsByTermIdAndBattaionAndCompany(@RequestParam(required = false) Long termId,
			@RequestParam(required = false) String battalion, @RequestParam(required = false) String company,
			@RequestParam(required = false) String serviceId, @RequestParam Integer pageNo,
			@RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		WeaponTrainingResultPayload response = weaponTrainingResultService
				.getCadetsByTermIdAndBattaionAndCompany(termId, battalion, company, serviceId, pageable);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/get_all_weapon_training_result_by_search")
	public ResponseEntity<?> getCadetsBySearch(@RequestParam(required = false) String serviceId,
			@RequestParam Long termId, @RequestParam Integer pageNo, @RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		WeaponTrainingResultPayload response = weaponTrainingResultService.getCadetsBySearch(serviceId, termId,
				pageable);

		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@PutMapping(value = "/save_bulk_weapon_training_result")
	public ResponseEntity<?> updateBulkOqMarksResult(
			@RequestBody List<WeaponTrainingResultFilterPayload> weaponTrainingResultPayloadList) throws MyException {
		String response = weaponTrainingResultService.updateBulkWeaponTrainingResult(weaponTrainingResultPayloadList);
		if (response.equalsIgnoreCase("success")) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

}