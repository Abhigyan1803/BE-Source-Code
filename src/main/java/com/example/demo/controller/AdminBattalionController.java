package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Battalion;
import com.example.demo.model.BattalionCompany;
import com.example.demo.model.BattalionHistory;
import com.example.demo.model.BattalionOrganizationChart;
import com.example.demo.model.RunbackRouteMr;
import com.example.demo.model.TrgBattalionPost;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.RouteRunMrFilterPayload;
import com.example.demo.payload.RouteRunMrPayload;
import com.example.demo.service.AdminBattalionService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/battalion")
public class AdminBattalionController {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdminBattalionService battalionService;

	@PostMapping(value = "/add-battalion")
	public ResponseEntity<?> addBattalion(@RequestBody Battalion battalion, ServletRequest request) throws MyException {

		Battalion response = battalionService.createBattalion(battalion);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "battalion," + ConstantMessage.BATTALION_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.BATTALION_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-battalion-list")
	public ResponseEntity<?> BattalionList() {
		List<Battalion> list = battalionService.getAllBattalionList();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-battalion")
	public ResponseEntity<?> updateBattalion(@RequestBody Battalion battalion, ServletRequest request)
			throws MyException {
		Battalion response = battalionService.updateBattalion(battalion);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",updated," + "battalion," + ConstantMessage.BATTALION_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.BATTALION_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-company-list")
	public ResponseEntity<?> companyList() throws MyException {
		List<BattalionCompany> list = battalionService.getAllBattalionCompany();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PostMapping(value = "/add-organization")
	public ResponseEntity<?> addOrganization(@RequestParam(required = false, value = "document") MultipartFile document,
			BattalionOrganizationChart btOrg, ServletRequest request) throws MyException {
		String profile_img = StringUtils.EMPTY;
		profile_img = FileUploader.uploadProfileImage(document, UploadDir);
		btOrg.setImage(url + profile_img);

		BattalionOrganizationChart response = battalionService.addBattalionOrganization(btOrg);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "organization,"
				+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response), HttpStatus.OK);
	}

	@PostMapping(value = "/update-organization")
	public ResponseEntity<?> updateOrganization(
			@RequestParam(required = false, value = "document") MultipartFile document,
			BattalionOrganizationChart btOrg, ServletRequest request) throws MyException {
		String profile_img = StringUtils.EMPTY;
		if (document != null && !document.isEmpty()) {
			profile_img = FileUploader.uploadProfileImage(document, UploadDir);
			btOrg.setImage(url + profile_img);
		}

		BattalionOrganizationChart response = battalionService.updateBattalionOrganization(btOrg);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "organization,"
				+ ConstantMessage.RECORD_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-organization-list")
	public ResponseEntity<?> getAllOrganization(int battalionId, int status) throws MyException {
		List<BattalionOrganizationChart> orgList = battalionService.getAllBattalionOrg(battalionId, status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, orgList),
				HttpStatus.OK);
	}

	@PostMapping(value = "/view-organization")
	public ResponseEntity<?> viewOrganizationById(Long id) throws MyException {
		BattalionOrganizationChart response = battalionService.viewBattalionOrg(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping(value = "/update-status-org")
	public ResponseEntity<?> updateOrganizationStatus(Long id, int status, ServletRequest request) throws MyException {
		BattalionOrganizationChart response = battalionService.updateOrganizationStatus(id, status);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "organization,"
				+ ConstantMessage.RECORD_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping(value = "/add-battalion-history")
	public ResponseEntity<?> addHistory(@RequestParam(required = false, value = "document") MultipartFile document,
			BattalionHistory history, ServletRequest request) throws MyException {
		String profile_img = StringUtils.EMPTY;
		if (document != null && !document.isEmpty()) {
			profile_img = FileUploader.uploadProfileImage(document, UploadDir);
			history.setImage(url + profile_img);
		}
		BattalionHistory response = battalionService.addBattalionHistory(history);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "battalion-history,"
				+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response), HttpStatus.OK);
	}

	@PostMapping(value = "/update-battalion-history")
	public ResponseEntity<?> updateHistory(@RequestParam(required = false, value = "document") MultipartFile document,
			BattalionHistory history, ServletRequest request) throws MyException {
		String profile_img = StringUtils.EMPTY;
		if (document != null && !document.isEmpty()) {
			profile_img = FileUploader.uploadProfileImage(document, UploadDir);
			history.setImage(url + profile_img);
		}
		BattalionHistory response = battalionService.updateHistory(history);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "battalion-history,"
				+ ConstantMessage.RECORD_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping(value = "/view-history")
	public ResponseEntity<?> viewHistoryById(Long id) throws MyException {
		BattalionHistory response = battalionService.viewHistoryById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-history-list")
	public ResponseEntity<?> getHistoryList() throws MyException {
		List<BattalionHistory> response = battalionService.getAllHistory();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping(value = "/update-history-status")
	public ResponseEntity<?> updateHistoryStatus(Long id, int status, ServletRequest request) throws MyException {
		BattalionHistory response = battalionService.updateHistoryStatus(id, status);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated,"
				+ "battalion-history-status," + ConstantMessage.RECORD_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping(value = "/get-company-by-battalion")
	public ResponseEntity<?> getBattalionSpecificCompany(int id) throws MyException {
		List<BattalionCompany> response = battalionService.getBattalionSpecificCompany(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping(value = "/latest-active-record") // latest active record of battalion history by battalion
	public ResponseEntity<?> activeBattalionHistoryRecord(Integer battalionId) throws MyException {
		BattalionHistory response = battalionService.activeBattalionHistoryRecord(battalionId);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/battalion-post-list")
	public ResponseEntity<?> BattalionPostList() {
		List<TrgBattalionPost> list = battalionService.getBattalionPostList();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PostMapping(value = "/add_runBack_route_mr")
	public ResponseEntity<?> addRunBackRouteMr(@RequestBody RunbackRouteMr runbackRouteMr, ServletRequest request)
			throws MyException {

		RunbackRouteMr response1 = battalionService.getResultByServiceIdAndResultTypeAndTermId(
				runbackRouteMr.getServiceId(), runbackRouteMr.getResultType(), runbackRouteMr.getTermId());
		if (response1 == null) {
			RunbackRouteMr response = battalionService.createRunbackRouteMr(runbackRouteMr);
			if (response != null) {
				return new ResponseEntity<>(
						new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_ADD, HttpStatus.OK, response),
						HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@PostMapping(value = "/update_runBack_route_mr")
	public ResponseEntity<?> updateRunBackRouteMr(@RequestBody RunbackRouteMr runbackRouteMr, ServletRequest request)
			throws MyException {

		RunbackRouteMr response = battalionService.updateRunbackRouteMr(runbackRouteMr);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_runBack_route_mr_by_serviceId_and_resultType")
	public ResponseEntity<?> getResultByServiceIdAndResultType(@RequestParam String serviceId,
			@RequestParam String resultType, ServletRequest request) throws MyException {

		RunbackRouteMr response = battalionService.getResultByServiceIdAndResultType(serviceId, resultType);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_all_runBack_route_mr_by_status")
	public ResponseEntity<?> getAllByStatus(@RequestParam Integer status, ServletRequest request) throws MyException {

		List<RunbackRouteMr> response = battalionService.getAllByStatus(status);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_sum_of_runBack_route_march_by_serviceId_and_termId")
	public ResponseEntity<?> getSumByServiceIdAndTermId(@RequestParam String serviceId, @RequestParam Long termId,
			ServletRequest request) throws MyException {

		RunbackRouteMr response = battalionService.getResultByServiceIdAndTermId(serviceId, termId);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/get_runBack_route_mr_by_serviceId_and_resultType_and_termId")
	public ResponseEntity<?> getResultByServiceIdAndResultTypeAndTermId(@RequestParam String serviceId,
			@RequestParam String resultType, @RequestParam Long termId, ServletRequest request) throws MyException {

		RunbackRouteMr response = battalionService.getResultByServiceIdAndResultTypeAndTermId(serviceId, resultType,
				termId);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_all_cadet_route_run_mr_by_termId_battalion_company")
	public ResponseEntity<?> getCadetsResultByTermIdAndBattaionAndCompany(@RequestParam String resultType,
			@RequestParam(required = false) Long termId, @RequestParam(required = false) String battalion,
			@RequestParam(required = false) String company, @RequestParam Integer pageNo,
			@RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		RouteRunMrPayload response = battalionService.getCadetsByTermIdAndBattaionAndCompany(resultType, termId,
				battalion, company, pageable);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_all_route_run_mr_by_search")
	public ResponseEntity<?> getCadetsResultBySearch(@RequestParam Long termId,
			@RequestParam(required = false) String resultType, @RequestParam(required = false) String serviceId,
			@RequestParam Integer pageNo, @RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		RouteRunMrPayload response = battalionService.getCadetsResultBySearch(termId, resultType, serviceId, pageable);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@PutMapping(value = "/save_bulk_route_run_mr_result")
	public ResponseEntity<?> updateBulkRouteRunMrResult(
			@RequestBody List<RouteRunMrFilterPayload> routeRunMrPayloadList) throws MyException {
		String response = battalionService.updateBulkRouteRunMrResult(routeRunMrPayloadList);
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
