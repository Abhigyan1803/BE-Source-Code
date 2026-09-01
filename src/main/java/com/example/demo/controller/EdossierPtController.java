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

import com.example.demo.model.EdossierPtResult;
import com.example.demo.model.EdossierPtSubject;
import com.example.demo.model.EdossierPtSubjectResult;
import com.example.demo.model.PtMotivationAwards;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.EdossierPtResultFilterPayload;
import com.example.demo.payload.EdossierPtResultPayload;
import com.example.demo.service.EdossierPtService;
import com.example.demo.service.EdossierPtSubjectService;
import com.example.demo.service.PtMotivationAwardsService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.ResponseMessage;

@CrossOrigin
@RestController
@RequestMapping("/api/edossier_pt_controller")
public class EdossierPtController {
	@Autowired
	private EdossierPtService service;

	@Autowired
	private EdossierPtSubjectService subService;

	@Autowired
	private PtMotivationAwardsService motivationService;

	@PostMapping(value = "/add_edossier_pt_result")
	public ResponseEntity<?> addEdossierPtResult(@RequestBody EdossierPtResult edossierPtResult) throws MyException {
		if (edossierPtResult != null) {

			List<EdossierPtSubjectResult> ptSubResult = edossierPtResult.getEdossierPtSubjectResult();
			if (ptSubResult != null) {

				for (EdossierPtSubjectResult ptSubRslt : ptSubResult) {
					service.createSubResult(ptSubRslt);
				}
			} else {
				return new ResponseEntity<>("Invalid edossier pt subject Details ", HttpStatus.NO_CONTENT);
			}
			EdossierPtResult response = service.createEdossierPtResult(edossierPtResult);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid edossier pt Details ", HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(value = "/get_edossier_pt_check")
	public ResponseEntity<?> getEdossierPtResultById(@RequestParam String serviceId, @RequestParam Long termId,
			@RequestParam String subjectType) {
		EdossierPtResult response = service.findByServiceIdAndTermIdAndSubjectType(serviceId, termId, subjectType);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			List<EdossierPtSubject> list = subService.getPtSubjectList(1, subjectType, termId);
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ADD, HttpStatus.OK, list), HttpStatus.OK);
		}

	}

	@PutMapping(value = "/update_edossier_pt_result")
	public ResponseEntity<?> updateEdossierPtResult(@RequestBody EdossierPtResult edossierPtResult) throws MyException {
		EdossierPtResult response = service.updateEdossierPtResult(edossierPtResult);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping("/add_pt_motivation_awards")
	public ResponseEntity<?> addPtMotivationAwards(@RequestBody PtMotivationAwards ptMotivationAwards) {
		PtMotivationAwards alreadyExist = motivationService.findByServiceIdAndTermId(ptMotivationAwards.getServiceId(),
				ptMotivationAwards.getTermId());
		if (alreadyExist == null) {
			PtMotivationAwards response = motivationService.addPtMotivationAwards(ptMotivationAwards);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/get_pt_motivation_awards_check")
	public ResponseEntity<?> getPtMotivationAwardsById(@RequestParam String serviceId, @RequestParam Long termId) {
		PtMotivationAwards response = motivationService.findByServiceIdAndTermId(serviceId, termId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@PutMapping(value = "/update_pt_motivation_awards")
	public ResponseEntity<?> updatePtMotivationAwards(@RequestBody PtMotivationAwards ptMotivationAwards)
			throws MyException {
		PtMotivationAwards response = motivationService.updatePtMotivationAwards(ptMotivationAwards);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get_all_cadet_pt_result_by_termId_battalion_company")
	public ResponseEntity<?> getCadetsByTermIdAndBattaionAndCompany(@RequestParam(required = false) Long termId,
			@RequestParam(required = false) String battalion, @RequestParam(required = false) String company,
			@RequestParam String subjectType, @RequestParam(required = false) String serviceId,
			@RequestParam(required = false) Integer pageNo, @RequestParam(required = false) Integer pageSize) {
		EdossierPtResultPayload response = null;
		if (pageNo != null && pageSize != null) {
			System.out.println("with pagination");
			Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
			response = service.getCadetsByTermIdAndBattaionAndCompanyAndSubjectType(termId, battalion, company,
					subjectType, serviceId, pageable);
		} else {
			System.out.println("without pagination");
			// FileUploader.paginationData(pageNo, pageSize);
			response = service.getCadetsByTermIdAndBattaionAndCompanyAndSubjectTypeWithoutPagination(termId, battalion,
					company, subjectType, serviceId);
		}

		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/get_all_pt_result_by_search")
	public ResponseEntity<?> getCadetsBySearch(@RequestParam(required = false) String serviceId,
			@RequestParam Long termId, @RequestParam String subjectType, @RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize) {

		EdossierPtResultPayload response = null;
		if (pageNo != null && pageSize != null) {
			Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
			response = service.getCadetsBySearch(serviceId, termId, subjectType, pageable);
		} else {
			response = service.getCadetsBySearchWithoutPagination(serviceId, termId, subjectType);
		}

		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@PutMapping(value = "/save_bulk_edossier_pt_result")
	public ResponseEntity<?> updateBulkOqMarksResult(
			@RequestBody List<EdossierPtResultFilterPayload> edossierPtResultPayloadList) throws MyException {
		String response = service.updateBulkEdossierPtResult(edossierPtResultPayloadList);
		if (response.equalsIgnoreCase("success")) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_ed_trg_eqtn_result_by_service_id_and_subject_type")
	public ResponseEntity<?> getEdossierPtResultById(@RequestParam String serviceId,
			@RequestParam(required = false) String subjectType) {
		List<EdossierPtResult> response = service.findByServiceIdAndSubjectType(serviceId, subjectType);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}
}
