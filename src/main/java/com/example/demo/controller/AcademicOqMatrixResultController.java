package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AcademicOqMatrixResult;
import com.example.demo.model.AcademicOqMatrixSubjectResult;
import com.example.demo.model.AcademicOqSubject;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.OqMatrixFilterPayload;
import com.example.demo.payload.OqMatrixPayload;
import com.example.demo.payload.OqMatrixTermPayload;
import com.example.demo.service.AcademicOqMatrixResultsService;
import com.example.demo.service.AcademicOqMatrixSubjectResultService;
import com.example.demo.service.AcademicOqSubjectService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/academicOqMatrixResultController")
@CrossOrigin
public class AcademicOqMatrixResultController {

	@Autowired
	private AcademicOqMatrixResultsService academicOqMatrixResultsService;

	@Autowired
	private AcademicOqMatrixSubjectResultService academicOqMatrixSubjectResultService;

	@Autowired
	AcademicOqSubjectService academicOqSubjectService;

	@PostMapping(value = "/add_academic_oq_mark_result")
	public ResponseEntity<?> addAcademicOqMarkResult(@RequestBody AcademicOqMatrixResult academicOqMatrixResult)
			throws MyException {
		if (academicOqMatrixResult != null) {

			List<AcademicOqMatrixSubjectResult> oqSubResult = academicOqMatrixResult.getAcademicOqMatrixSubjectResult();
			if (oqSubResult != null) {

				for (AcademicOqMatrixSubjectResult oqSubRslt : oqSubResult) {
					academicOqMatrixSubjectResultService.createSubResult(oqSubRslt);
				}
			} else {
				return new ResponseEntity<>("Invalid academic oq subject Details ", HttpStatus.NO_CONTENT);
			}
			AcademicOqMatrixResult response = academicOqMatrixResultsService
					.createAcademicOqMarkResult(academicOqMatrixResult);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid academic oq marks Details ", HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(value = "/get_academic_oq_result_check")
	public ResponseEntity<?> getAcademicOqResulttById(@RequestParam String serviceId, @RequestParam int termId,
			@RequestParam String termType) {
		AcademicOqMatrixResult response = academicOqMatrixResultsService.findByServiceIdAndTermIdAndTermType(serviceId,
				termId, termType);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			List<AcademicOqSubject> list = academicOqSubjectService.getAcademicOqSubjectList(1);
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ADD, HttpStatus.OK, list), HttpStatus.OK);
		}

	}

//	@GetMapping(value = "/get-camp-mark-result-check")
//	public ResponseEntity<?> getCampMarkRsltcheck(@RequestParam String serviceId, @RequestParam int termId,
//			@RequestParam long exerciseTypeId) throws Exception {
//		JSONObject campMarksResults = academicOqMatrixResultsService
//				.findByServiceIdAndTermIdAndExerciseTypeId(serviceId, termId, exerciseTypeId);
//		if (campMarksResults != null) {
//			HashMap<String, Object> yourHashMap = new Gson().fromJson(campMarksResults.toString(), HashMap.class);
//			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.UPDATE, HttpStatus.OK, yourHashMap),
//					HttpStatus.OK);
//		} else {
//			List<AcademicOqSubject> list = academicOqSubjectService.getAcademicOqSubjectList(1);
//			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ADD, HttpStatus.OK, list), HttpStatus.OK);
//		}
//
//	}

	@PutMapping(value = "/update_academic_oq_mark_result")
	public ResponseEntity<?> updateAcademicOqMarkResult(@RequestBody AcademicOqMatrixResult academicOqMatrixResult)
			throws MyException {
		AcademicOqMatrixResult response = academicOqMatrixResultsService
				.updateAcademicOqMarkResult(academicOqMatrixResult);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get_all_cadet_oq_matrix_by_termId_battalion_company")
	public ResponseEntity<?> getCadetsByTermIdAndBattaionAndCompany(@RequestParam(required = false) Long termId,
			@RequestParam String termType, @RequestParam(required = false) String battalion,
			@RequestParam(required = false) String company, @RequestParam(required = false) String serviceId,
			@RequestParam Integer pageNo, @RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		OqMatrixPayload response = academicOqMatrixResultsService.getCadetsByTermIdAndTermTypeAndBattaionAndCompany(
				termId, termType, battalion, company, serviceId, pageable);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@PutMapping(value = "/save_bulk_academic_oq_matrix_result")
	public ResponseEntity<?> updateBulkAcademicOqMarkResult(
			@RequestBody List<OqMatrixFilterPayload> oqMatrixPayloadList) throws MyException {
		String response = academicOqMatrixResultsService.updateBulkAcademicOqMarkResult(oqMatrixPayloadList);
		if (response.equalsIgnoreCase("success")) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_all_academic_oq_matrix_result_by_search")
	public ResponseEntity<?> getCadetsBySearch(@RequestParam Long termId, @RequestParam String termType,
			@RequestParam(required = false) String serviceId, @RequestParam Integer pageNo,
			@RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		OqMatrixPayload response = academicOqMatrixResultsService.getCadetsBySearch(termId, termType, serviceId,
				pageable);

		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_oqmatrix_drill_eqtn")
	public ResponseEntity<?> getOqMatrixDrillEqtn(@RequestParam String serviceId) {

		OqMatrixTermPayload response = academicOqMatrixResultsService.findOqMatrixDrillEqtn(serviceId);

		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

}
