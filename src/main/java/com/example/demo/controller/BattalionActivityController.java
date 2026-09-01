package com.example.demo.controller;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//import com.example.demo.model.BattalionAward;
import com.example.demo.service.BattallionActitvityService;

@RestController
@RequestMapping("/api/battallionActivityController")
public class BattalionActivityController {

	@Autowired
	BattallionActitvityService battallionActitvityService;
	
	@PostMapping("/addBattallionAward")
	Map<Object, Object> addBatallioActivity(@RequestParam MultipartFile img,@RequestParam Integer battalionId,ServletRequest servletRequest)
	{
		return  battallionActitvityService.addBattallionActivity(img,battalionId,servletRequest);
	}

	@PostMapping("/getAllAwards")
	Map<Object, Object> getAllActivityByStatus(@RequestParam int status)
	{
	return	battallionActitvityService.getBattalionActivityByStatus(status);
	}

	@PostMapping("/activeDeActiveAwards")
	Map<Object, Object> ActiveDeactiveActivity(@RequestParam Long id , @RequestParam int status,ServletRequest servletRequest)
	{
		return battallionActitvityService.activeDeactiveActivity(id, status,servletRequest);
	}
	
}
