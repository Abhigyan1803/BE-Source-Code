package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.myexception.MyException;
import com.example.demo.payload.OqDrillFilterPayload;
import com.example.demo.payload.OqDrillPayload;
import com.example.demo.payload.OqEqtnFilterPayload;
import com.example.demo.payload.OqEqtnPayload;
import com.example.demo.service.OqDrillResultService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.ResponseMessage;

@CrossOrigin
@RestController
@RequestMapping("/api/oq_drill_result_controller")
public class OqDrillResultController {

	@Autowired
	private OqDrillResultService oqDrillResultService;
	
	@GetMapping(value = "/get_all_oq_drill_result_by_search")
	public ResponseEntity<?> getCadetsBySearch(@RequestParam Long termId,@RequestParam String termType,
			@RequestParam(required = false) String serviceId, @RequestParam Integer pageNo,
			@RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		OqDrillPayload response = oqDrillResultService.getCadetsBySearch(termId,termType, serviceId, pageable);

		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}
	
	@GetMapping(value = "/get_all_cadet_oq_drill_result_by_termId_battalion_company")
	public ResponseEntity<?> getCadetsByTermIdAndBattaionAndCompany(@RequestParam(required = false) Long termId,@RequestParam String termType,
			@RequestParam(required = false) String battalion, @RequestParam(required = false) String company,
			@RequestParam(required = false) String serviceId, @RequestParam Integer pageNo,
			@RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		OqDrillPayload response = oqDrillResultService.getCadetsByTermIdAndTermTypeAndBattaionAndCompany(termId,termType,
				battalion, company, serviceId, pageable);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}
	
	@PutMapping(value = "/save_bulk_oq_drill_result")
	public ResponseEntity<?> updateBulkOqEqtnResult(
			@RequestBody List<OqDrillFilterPayload> oqDrillPayloadList) throws MyException {
		String response = oqDrillResultService.updateBulkOqdrillResult(oqDrillPayloadList);
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
