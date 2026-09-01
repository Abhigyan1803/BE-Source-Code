package com.example.demo.controller;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.BattalionAward;
import com.example.demo.payload.AddBatalionAwardPayLoad;
import com.example.demo.payload.GetDataOnlyById;
import com.example.demo.service.BattallionAwardService;

@RestController
@CrossOrigin
@RequestMapping("/api/battallionAwardController")
public class BattalionAwardController {

	@Autowired
	BattallionAwardService battallionAwardService;

	@PostMapping("/addBattallionAward")
	Map<Object, Object> addBatallioAward(AddBatalionAwardPayLoad request, MultipartFile img,
			ServletRequest servletRequest) {
		return battallionAwardService.addBatallioAward(request, img, servletRequest);
	}

	@PostMapping("/getAllAwards")
	Map<Object, Object> getAllAwards(@RequestParam(defaultValue = "2") Integer status,
			@RequestParam(defaultValue = "0") Integer battalionId) {
		return battallionAwardService.getAllAwards(battalionId, status);
	}

	@PostMapping("/updateAwards")
	Map<Object, Object> updateAwards(BattalionAward request, MultipartFile img, ServletRequest servletRequest) {
		return battallionAwardService.updateAwards(request, img, servletRequest);
	}

	@PostMapping("/activeDeActiveAwards")
	Map<Object, Object> ActiveDeactiveAward(@RequestParam Long id, @RequestParam int status,
			ServletRequest servletRequest) {
		return battallionAwardService.ActiveDeactiveAward(id, status, servletRequest);
	}

	@PostMapping("/getDetailsByOnlyById")
	Map<Object, Object> getDetailsByOnlyById(@RequestBody GetDataOnlyById request) {
		return battallionAwardService.getDetailsByOnlyById(request);
	}
}
