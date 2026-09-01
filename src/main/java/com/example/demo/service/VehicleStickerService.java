package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.VehicleStickers;

public interface VehicleStickerService {
	
	VehicleStickers addDetails(VehicleStickers  record,MultipartFile docFile);
	
	VehicleStickers updateDetails(VehicleStickers record , MultipartFile docFile);
	
	List<VehicleStickers> getList(int status);
	
	VehicleStickers viewById(Long id);
	
	VehicleStickers changeStatus(int status , Long id);


}
