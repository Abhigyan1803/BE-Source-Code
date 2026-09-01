package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.VehicleStickers;
import com.example.demo.service.VehicleStickerService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RequestMapping("/api/vehicle-stickers")
@RestController
@CrossOrigin
public class VehicleStickersController {

	@Autowired
	VehicleStickerService vehicleStickersService;
	
	@PostMapping("/add-vehicle-stickers")
	public ResponseEntity<?> addDetails(VehicleStickers details , MultipartFile docfile , ServletRequest request)
	{
		
		VehicleStickers response = vehicleStickersService.addDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-vehicle-stickers,"
				+ ConstantMessage.VEHICLE_STICKERS_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.VEHICLE_STICKERS_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-vehicle-stickers")
	public ResponseEntity<?> updateDetails(VehicleStickers details , MultipartFile docfile , ServletRequest request)
	{
		
		VehicleStickers response = vehicleStickersService.updateDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-vehicle-stickers,"
				+ ConstantMessage.VEHICLE_STICKERS_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.VEHICLE_STICKERS_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-status")
	public ResponseEntity<?> changeStatus(Long id , int status,ServletRequest request)
	{
		
		VehicleStickers response = vehicleStickersService.changeStatus(status, id);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-vehicle-stickers-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-by-Id")
	public ResponseEntity<?> viewCVRById(Long id)
	{
		VehicleStickers response = vehicleStickersService.viewById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-list")
	public ResponseEntity<?> getCVRList(int status)
	{
		List<VehicleStickers> response = vehicleStickersService.getList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	
	
	
}
