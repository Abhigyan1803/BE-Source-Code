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

import com.example.demo.model.TRG_EQTNResult;
import com.example.demo.model.TRG_EQTNSubject;
import com.example.demo.model.TRG_EQTNSubjectResult;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.EqtnFilterPayload;
import com.example.demo.payload.EqtnPayload;
import com.example.demo.service.TRG_EQTNResultService;
import com.example.demo.service.TRG_EQTNSubjectResultService;
import com.example.demo.service.TRG_EQTNSubjectService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/trg-eqtn-result-controller")
@CrossOrigin
public class TRG_EQTNResultController {

	@Autowired
	private TRG_EQTNResultService tRG_EQTNResultService;
	@Autowired
	private TRG_EQTNSubjectResultService tRG_EQTNSubjectResultService;
	@Autowired
	private TRG_EQTNSubjectService tRG_EQTNSubjectService;

	@PostMapping(value = "/add_trg_eqtn_result")
	public ResponseEntity<?> addTRG_EQTNResult(@RequestBody TRG_EQTNResult tRG_EQTNResult) throws MyException {
		if (tRG_EQTNResult != null) {

			List<TRG_EQTNSubjectResult> tRG_EQTNSubjectResult = tRG_EQTNResult.getTrgEQTNSubResult();
			if (tRG_EQTNSubjectResult != null) {
				for (TRG_EQTNSubjectResult EQTNSubRslt : tRG_EQTNSubjectResult) {
					tRG_EQTNSubjectResultService.createSubResult(EQTNSubRslt);
				}
			} else {
				return new ResponseEntity<>("Invalid credit for excellence subject Details ", HttpStatus.NO_CONTENT);
			}
			TRG_EQTNResult response = tRG_EQTNResultService.createTRG_EQTNResult(tRG_EQTNResult);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid trg eqtn marks Details ", HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value = "/get_trg_eqtn_result_check")
	public ResponseEntity<?> getTRG_EQTNResultById(@RequestParam String serviceId, @RequestParam Long termId) {
		TRG_EQTNResult response = tRG_EQTNResultService.findByServiceIdAndTermId(serviceId, termId);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			List<TRG_EQTNSubject> result = tRG_EQTNSubjectService.getByStatusAndTermId(1, termId);
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ADD, HttpStatus.OK, result), HttpStatus.OK);
		}
	}

	@PutMapping(value = "/update-trg_eqtn_result")
	public ResponseEntity<?> updateTRG_EQTNResult(@RequestBody TRG_EQTNResult tRG_EQTNResult) throws MyException {
		TRG_EQTNResult response = tRG_EQTNResultService.updateTRG_EQTNResult(tRG_EQTNResult);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
//	@PutMapping(value = "/update-academic_credit_for_exellence_result")
//	public ResponseEntity<?> updateAcademicCreditForExcellenceResult(
//			@RequestBody AcademicCreditForExcellenceResult academicCreditForExcellenceResult) throws MyException {
//		AcademicCreditForExcellenceResult response = academicCreditForExcellenceResultService
//				.createAcademicCreditForExcellenceResult(academicCreditForExcellenceResult);
//		return new ResponseEntity<>(
//				new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
//				HttpStatus.OK);
//	}

	@GetMapping(value = "/get_ed_trg_eqtn_result_by_service_id")
	public ResponseEntity<?> getTRG_EQTNResultById(@RequestParam String serviceId) {
		List<TRG_EQTNResult> response = tRG_EQTNResultService.findByServiceId(serviceId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/get_all_cadet_trg_eqtn_result_by_termId_battalion_company")
	public ResponseEntity<?> getCadetsByTermIdAndBattaionAndCompany(@RequestParam(required = false) Long termId,
			@RequestParam(required = false) String battalion, @RequestParam(required = false) String company,
			@RequestParam(required = false) String serviceId, @RequestParam Integer pageNo,
			@RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		EqtnPayload response = tRG_EQTNResultService.getCadetsByTermIdAndBattaionAndCompany(termId, battalion, company,
				serviceId, pageable);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@PutMapping(value = "/save_bulk_trg_eqtn_result")
	public ResponseEntity<?> updateBulkTRG_EQTNResult(@RequestBody List<EqtnFilterPayload> eqtnPayloadList)
			throws MyException {
		String response = tRG_EQTNResultService.updateBulkTRG_EQTNResult(eqtnPayloadList);
		if (response.equalsIgnoreCase("success")) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/get_all_eqtn_for_excelle_by_search")
	public ResponseEntity<?> getCadetsBySearch(@RequestParam Long termId,
			@RequestParam(required = false) String serviceId, @RequestParam Integer pageNo,
			@RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		EqtnPayload response = tRG_EQTNResultService.getCadetsBySearch(termId, serviceId, pageable);

		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}
}
