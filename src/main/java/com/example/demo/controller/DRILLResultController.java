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

import com.example.demo.model.DRILLResult;
import com.example.demo.model.DRILLSubject;
import com.example.demo.model.EdDrillTerm3Dat;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.DrillFilterPayload;
import com.example.demo.payload.DrillPayload;
import com.example.demo.service.DRILLResultService;
import com.example.demo.service.DRILLSubjectService;
import com.example.demo.service.EdDrillTerm3DatService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/drillResultController")
@CrossOrigin
public class DRILLResultController {
	@Autowired
	public DRILLResultService dRILLResultService;

	@Autowired
	public DRILLSubjectService drillSubjectService;

	@Autowired
	private EdDrillTerm3DatService edDrillTerm3DatService;

	@PostMapping(value = "/saveDRILLResult")
	public ResponseEntity<?> addDRILLResult(@RequestBody DRILLResult dRILLResult) throws MyException {
		// List<CadetCampCoyCdrResult> response =
		// cadetCampCoyService.addCampCoyResult(result);
		DRILLResult response = dRILLResultService.createDrillResult(dRILLResult);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.DRILL_RESULT_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-drill-result")
	public ResponseEntity<?> getDrillResult(@RequestParam String serviceId, @RequestParam Long termId)
			throws Exception {
		DRILLResult drillRslt = dRILLResultService.getDrillResult(serviceId, termId);
		if (drillRslt != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.UPDATE, HttpStatus.OK, drillRslt),
					HttpStatus.OK);
		} else {
			List<DRILLSubject> drillSubject = drillSubjectService.getAllSubjectByTermIdAndStatus(termId, 1);
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ADD, HttpStatus.OK, drillSubject),
					HttpStatus.OK);
		}
	}

	@PutMapping(value = "/update-drill_result")
	public ResponseEntity<?> updateDrillResult(@RequestBody DRILLResult drillResult) throws MyException {
		DRILLResult response = dRILLResultService.updateDrillResult(drillResult);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.DRILL_RESULT_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-all-drill-result")
	public ResponseEntity<?> getAllDrillResult(@RequestParam String serviceId) throws Exception {
		List<DRILLResult> drillRslt = dRILLResultService.getAllDrillResult(serviceId);
		if (drillRslt != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, drillRslt),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/get_all_drill-result_by_termId_battalion_company")
	public ResponseEntity<?> getCadetsByTermIdAndBattaionAndCompany(@RequestParam(required = false) Long termId,
			@RequestParam(required = false) String battalion, @RequestParam(required = false) String company,
			@RequestParam(required = false) String serviceId, @RequestParam Integer pageNo,
			@RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		DrillPayload response = dRILLResultService.getCadetsByTermIdAndBattaionAndCompanyAndEntryTypeId(termId,
				battalion, company, serviceId, pageable);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@PutMapping(value = "/save_bulk_drill_result")
	public ResponseEntity<?> updateDrillResult(@RequestBody List<DrillFilterPayload> drillPayloadList)
			throws MyException {
		String response = dRILLResultService.updateDrillResult(drillPayloadList);
		if (response.equalsIgnoreCase("success")) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/get_all_drill_result_by_search")
	public ResponseEntity<?> getCadetsBySearch(@RequestParam(required = false) String serviceId,
			@RequestParam Long termId, @RequestParam Integer pageNo, @RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		DrillPayload response = dRILLResultService.getCadetsBySearch(serviceId, termId, pageable);

		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_edossier_drill_3_term_dat")
	public ResponseEntity<?> getEdDrillTerm3DatByServiceIdAndStatus(@RequestParam String serviceId,
			@RequestParam Integer status) {
		EdDrillTerm3Dat response = edDrillTerm3DatService.findByServiceIdAndStatus(serviceId, status);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@PostMapping("/add_edossier_drill_3_term_dat")
	public ResponseEntity<?> addEdDrillTerm3Dat(@RequestBody EdDrillTerm3Dat edDrillTerm3Dat) {
		EdDrillTerm3Dat alreadyExist = edDrillTerm3DatService.findByServiceId(edDrillTerm3Dat.getServiceId());
		if (alreadyExist == null) {
			EdDrillTerm3Dat response = edDrillTerm3DatService.addEdDrillTerm3Dat(edDrillTerm3Dat);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@PutMapping(value = "/update_edossier_drill_3_term_dat")
	public ResponseEntity<?> updateEdDrillTerm3Dat(@RequestBody EdDrillTerm3Dat edDrillTerm3Dat) throws MyException {
		EdDrillTerm3Dat response = edDrillTerm3DatService.updateEdDrillTerm3Dat(edDrillTerm3Dat);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
}
