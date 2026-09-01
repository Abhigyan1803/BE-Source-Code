package com.example.demo.controller;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.IMAActivitiesService;

@RestController
@RequestMapping("/api/imaActivityController")
@CrossOrigin
public class AdminIMAActivityController {

	@Autowired
	IMAActivitiesService imaService;

//	@ApiOperation(value="add ima activity")
	@PostMapping("/addIMAActivity")
	public Map<Object, Object> addActivity(@RequestParam(required = false, value = "image") MultipartFile image,
			int status, ServletRequest request) {
		return imaService.addActivity(image, status, request);
	}

//	@ApiOperation(value="add ima activity")
	@PostMapping("/getIMAActivities")
	public Map<Object, Object> getIMAActivities() {
		return imaService.getAllActivities();
	}

//	@ApiOperation(value="Get activity by status")
	@PostMapping("/getActivityByStatus")
	public Map<Object, Object> getActivitiesByStatus(int status) {
		return imaService.getActivitiesByStatus(status);
	}

//	@ApiOperation(value="Active deactive activities pass status : 1 for active and 0 for deactive")
	@PostMapping("/activeDeactiveActivity")
	public Map<Object, Object> activeDeactiveActivity(Long id, int status, ServletRequest request) {
		return imaService.activeDeactiveActivity(id, status, request);
	}
}
