package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AcdClubSops;
import com.example.demo.model.AcdCounsellor;
import com.example.demo.model.GCBoard;
import com.example.demo.model.GCBoard_Pcht_Ol_Achievements;
import com.example.demo.service.GCBoardService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/gc_board")
public class GCBoardController {

	@Autowired
	GCBoardService gcBoardService;

	@PostMapping("/add-gcBoard")
	public ResponseEntity<?> addDetails(GCBoard details,
			@RequestParam(value = "docfile", required = false) MultipartFile docfile, ServletRequest request) {
		GCBoard response = gcBoardService.addGCBoard(details, docfile);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "add-gcBoard," + ConstantMessage.GC_BOARD_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.GC_BOARD_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PutMapping("/update-gcBoard")
	public ResponseEntity<?> updateDetails(GCBoard details,
			@RequestParam(value = "docfile", required = false) MultipartFile docfile, ServletRequest request) {
		GCBoard response = gcBoardService.updateGCBoard(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-gcBoard,"
				+ ConstantMessage.GC_BOARD_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.GC_BOARD_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping("/change-status")
	public ResponseEntity<?> changeStatus(Long id, int status, ServletRequest request) {

		GCBoard response = gcBoardService.changeStatus(id, status);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update,"
				+ "change-gcBoard-status," + ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping("/view-by-Id")
	public ResponseEntity<?> viewById(Long id) {
		GCBoard response = gcBoardService.viewById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response), HttpStatus.OK);
	}

	@GetMapping("/get-list")
	public ResponseEntity<?> getList(int status) {
		List<GCBoard> response = gcBoardService.getList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response), HttpStatus.OK);
	}

	@PostMapping("/add-gcBoard_Pcht_Ol_Achievements")
	public ResponseEntity<?> addDetailsGCBoard_Pcht_Ol_Achievements(GCBoard_Pcht_Ol_Achievements details,
			@RequestParam(value = "docfile", required = false) MultipartFile docfile, ServletRequest request) {
		GCBoard_Pcht_Ol_Achievements response = gcBoardService.addDetailsGCBoard_Pcht_Ol_Achievements(details, docfile);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "add-gcBoard," + ConstantMessage.GC_BOARD_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.GC_BOARD_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping("/view-gcBoard_Pcht_Ol_Achievements-by-Id")
	public ResponseEntity<?> viewGCBoard_Pcht_Ol_AchievementsById(Long id) {
		GCBoard_Pcht_Ol_Achievements response = gcBoardService.viewGCBoard_Pcht_Ol_AchievementsById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response), HttpStatus.OK);
	}

	@GetMapping("/get-gcBoard_Pcht_Ol_AchievementsList")
	public ResponseEntity<?> getGCBoard_Pcht_Ol_AchievementsList(String type, String subType, int status) {
		List<GCBoard_Pcht_Ol_Achievements> response = gcBoardService.getGCBoard_Pcht_Ol_AchievementsList(type, subType,
				status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response), HttpStatus.OK);
	}

	@PutMapping("/update-gcBoard_Pcht_Ol_AchievementsDetails")
	public ResponseEntity<?> updateGCBoard_Pcht_Ol_AchievementsDetails(GCBoard_Pcht_Ol_Achievements details,
			@RequestParam(value = "docfile", required = false) MultipartFile docfile, ServletRequest request) {
		GCBoard_Pcht_Ol_Achievements response = gcBoardService.updateGCBoard_Pcht_Ol_AchievementsDetails(details,
				docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-gcBoard,"
				+ ConstantMessage.GC_BOARD_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.GC_BOARD_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PutMapping("/change-gcBoard_Pcht_Ol_AchievementsDetails-status")
	public ResponseEntity<?> changeGCBoard_Pcht_Ol_AchievementsDetailsStatus(Long id, int status,
			ServletRequest request) {
		GCBoard_Pcht_Ol_Achievements response = gcBoardService.changeGCBoard_Pcht_Ol_AchievementsDetailsStatus(id,
				status);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update,"
				+ "change-gcBoard-status," + ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping("/add_acd_club_sops")
	public ResponseEntity<?> addAcdClubSops(AcdClubSops acdClubSops,
			@RequestParam(value = "docfile", required = false) MultipartFile docfile, ServletRequest request) {
		AcdClubSops response = gcBoardService.addAcdClubSops(acdClubSops, docfile);

		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response), HttpStatus.OK);
	}

	@PutMapping("/update_acd_club_sops")
	public ResponseEntity<?> updateAcdClubSops(AcdClubSops acdClubSops,
			@RequestParam(value = "docfile", required = false) MultipartFile docfile, ServletRequest request) {
		AcdClubSops response = gcBoardService.updateAcdClubSops(acdClubSops, docfile);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_UPDATED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@GetMapping("/get_acd_club_sops_by_id")
	public ResponseEntity<?> getAcdClubSopsById(Long id) {
		AcdClubSops response = gcBoardService.getAcdClubSopsById(id);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@GetMapping("/get_acd_club_sops_list")
	public ResponseEntity<?> getAcdClubSopsList(String type, String subType, String subSubType, int status) {
		List<AcdClubSops> response = gcBoardService.getAcdClubSopsList(type, subType, subSubType, status);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}

	@PutMapping("/change_acd_club_sops_status")
	public ResponseEntity<?> changeAcdClubSopsStatus(Long id, Integer status, ServletRequest request) {
		AcdClubSops response = gcBoardService.changeAcdClubSopsStatus(id, status);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@PostMapping("/add_acd_counsellor")
	public ResponseEntity<?> addAcdCounsellor(@RequestBody AcdCounsellor acdCounsellor, ServletRequest request) {
		AcdCounsellor response = gcBoardService.addAcdCounsellor(acdCounsellor);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_ADD, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@PutMapping("/update_acd_counsellor")
	public ResponseEntity<?> updateAcdCounsellor(@RequestBody AcdCounsellor acdCounsellor, ServletRequest request) {
		AcdCounsellor response = gcBoardService.updateAcdCounsellor(acdCounsellor);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_UPDATED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@GetMapping("/get_acd_counsellor_by_id")
	public ResponseEntity<?> getAcdCounsellorById(@RequestParam Long id) {
		AcdCounsellor response = gcBoardService.getAcdCounsellorById(id);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@GetMapping("/get_acd_counsellor_list")
	public ResponseEntity<?> getAcdCounsellorList(@RequestParam(required = false) Long battalionId,
			@RequestParam(required = false) Long companyId, @RequestParam Integer status) {
		List<AcdCounsellor> response = gcBoardService.getAcdCounsellorList(battalionId, companyId, status);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}

}
