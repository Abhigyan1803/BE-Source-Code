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

import com.example.demo.model.AcademicLeadershipMatrixResult;
import com.example.demo.model.AcademicLeadershipMatrixSubjectResult;
import com.example.demo.model.AcademicLeadershipSubject;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.LeadershipFilterPayload;
import com.example.demo.payload.LeadershipPayload;
import com.example.demo.service.AcademicLeadershipMatrixResultService;
import com.example.demo.service.AcademicLeadershipMatrixSubjectResultService;
import com.example.demo.service.AcademicLeadershipSubjectService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/academic-leadership-matrix-result-controller")
@CrossOrigin
public class AcademicLeadershipMatrixResultController {
	@Autowired
	private AcademicLeadershipMatrixResultService academicLeadershipMatrixResultService;

	@Autowired
	private AcademicLeadershipMatrixSubjectResultService academicLeadershipMatrixSubjectResultService;

	@Autowired
	private AcademicLeadershipSubjectService academicLeadershipSubjectService;

	@PostMapping(value = "/add_academic_leadership_matrix_result")
	public ResponseEntity<?> addAcademicLeadershipMatrixResult(
			@RequestBody AcademicLeadershipMatrixResult academicLeadershipMatrixResult) throws MyException {
		if (academicLeadershipMatrixResult != null) {

			List<AcademicLeadershipMatrixSubjectResult> academicLeadershipMatrixSubjectResult = academicLeadershipMatrixResult
					.getLeadershipSubjectResult();
			if (academicLeadershipMatrixSubjectResult != null) {
				for (AcademicLeadershipMatrixSubjectResult leadershipSubRslt : academicLeadershipMatrixSubjectResult) {
					academicLeadershipMatrixSubjectResultService.createSubResult(leadershipSubRslt);
				}
			} else {
				return new ResponseEntity<>("Invalid leadership subject Details ", HttpStatus.NO_CONTENT);
			}
			AcademicLeadershipMatrixResult response = academicLeadershipMatrixResultService
					.createAcademicLeadershipMatrixResult(academicLeadershipMatrixResult);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid Academic leadership matrix marks Details ", HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(value = "/get_academic_leadership_matrix_result_check")
	public ResponseEntity<?> getAcademicLeadershipMatrixResultById(@RequestParam String serviceId,
			@RequestParam int termId) {
		AcademicLeadershipMatrixResult response = academicLeadershipMatrixResultService
				.findByServiceIdAndTermId(serviceId, termId);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			List<AcademicLeadershipSubject> result = academicLeadershipSubjectService.getBystatus(1);
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ADD, HttpStatus.OK, result), HttpStatus.OK);
		}

	}

	/*
	 * * @GetMapping(value = "/get-camp-mark-result-check") public ResponseEntity<?>
	 * getCampMarkRsltcheck(@RequestParam String serviceId, @RequestParam int
	 * termId,
	 *
	 * @RequestParam long exerciseTypeId) throws Exception { JSONObject
	 * campMarksResults =
	 * campMarksResultsService.findByServiceIdAndTermIdAndExerciseTypeId(serviceId,
	 * termId, exerciseTypeId); if (campMarksResults != null) { HashMap<String,
	 * Object> yourHashMap = new Gson().fromJson(campMarksResults.toString(),
	 * HashMap.class); return new ResponseEntity<>(new
	 * ResponseMessage(ConstantMessage.UPDATE, HttpStatus.OK, yourHashMap),
	 * HttpStatus.OK); } else { List<CampSubjectDetails> list =
	 * campSubjectDetailsService.getAllSubjectByStatus(1); return new
	 * ResponseEntity<>(new ResponseMessage(ConstantMessage.ADD, HttpStatus.OK,
	 * list), HttpStatus.OK); }
	 *
	 * }
	 */

	/*
	 * @GetMapping(value = "/get-gc-appt-all") public ResponseEntity<?>
	 * getGcApptAll() throws Exception { List<GcAppt> gcApptList =
	 * gcApptService.getGcAppt(); return new ResponseEntity<>(new
	 * ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, gcApptList),
	 * HttpStatus.OK); }
	 */
	@PutMapping(value = "/update-academic_leadership_matrix_result")
	public ResponseEntity<?> updateAcademicLeadershipMatrixResult(
			@RequestBody AcademicLeadershipMatrixResult academicLeadershipMatrixResult) throws MyException {
		AcademicLeadershipMatrixResult response = academicLeadershipMatrixResultService
				.updateAcademicLeadershipMatrixResult(academicLeadershipMatrixResult);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get_ed_leadership_matrix_result_by_serviceid")
	public ResponseEntity<?> getgetAcademicLeadershipMatrixResultByserviceid(@RequestParam String serviceId) {
		List<AcademicLeadershipMatrixResult> response = academicLeadershipMatrixResultService
				.findByServiceId(serviceId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/get_all_cadet_leadership_by_termId_battalion_company")
	public ResponseEntity<?> getCadetsByTermIdAndBattaionAndCompany(@RequestParam(required = false) Long termId,
			@RequestParam(required = false) String battalion, @RequestParam(required = false) String company,
			@RequestParam(required = false) String serviceId, @RequestParam Integer pageNo,
			@RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		LeadershipPayload response = academicLeadershipMatrixResultService
				.getCadetsByTermIdAndBattaionAndCompany(termId, battalion, company, serviceId, pageable);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@PutMapping(value = "/save_bulk_academic_leadership_matrix_result")
	public ResponseEntity<?> updateBulkAcademicLeadershipMatrixResult(
			@RequestBody List<LeadershipFilterPayload> leadershipPayloadList) throws MyException {
		String response = academicLeadershipMatrixResultService
				.updateBulkAcademicLeadershipMatrixResult(leadershipPayloadList);
		if (response.equalsIgnoreCase("success")) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_all_academic_leadership_matrix_result_by_search")
	public ResponseEntity<?> getCadetsBySearch(@RequestParam Long termId,
			@RequestParam(required = false) String serviceId, @RequestParam Integer pageNo,
			@RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		LeadershipPayload response = academicLeadershipMatrixResultService.getCadetsBySearch(termId, serviceId,
				pageable);

		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

}
