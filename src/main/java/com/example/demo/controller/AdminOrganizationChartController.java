package com.example.demo.controller;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.payload.OrganizationChartPayload;
import com.example.demo.service.OrganizationChartService;

@RestController
@RequestMapping("/api/OrganizationChartController")
@CrossOrigin
public class AdminOrganizationChartController {

	@Autowired
	OrganizationChartService orgService;

//	@CrossOrigin
//	@PostMapping("/addOrg")
//	public Map<Object, Object> addOrganization(@RequestParam(required = false, value = "image") MultipartFile image,
//			@RequestParam(value = "rank") String rank, @RequestParam(value = "name") String name,
//			@RequestParam(value = "position") Long position, @RequestParam(value = "status") int status,@RequestParam(value="award")String award) {
//		return orgService.addOrgPosition(name, rank, position, image, status,award);
//	}

	@CrossOrigin
	@PostMapping("/addOrg")
	public Map<Object, Object> addOrganization(@RequestParam(required = false, value = "image") MultipartFile image,
			OrganizationChartPayload payload, ServletRequest request) {
		return orgService.addOrgPosition(image, payload, request);
	}

	@PostMapping("/getAllOrg")
	public Map<Object, Object> getAllOrganization() {
		return orgService.getAllOrgPositions();
	}

//	@PostMapping("/updateOrg")
//	@CrossOrigin
//	public Map<Object, Object> updateOrganization(@RequestParam(required = false, value = "image") MultipartFile image,
//			@RequestParam(value = "id") Long id, @RequestParam(value = "rank") String rank,
//			@RequestParam(value = "name") String name, @RequestParam(value = "position") Long position,
//			@RequestParam(value = "status") int status,@RequestParam(value="award")String award) {
//		return orgService.updateOrgPosition(id, name, rank, position, image, status,award);
//	}

	@PostMapping("/updateOrg")
	public Map<Object, Object> updateOrganization(@RequestParam(required = false, value = "image") MultipartFile image,
			OrganizationChartPayload payload, ServletRequest request) {
		return orgService.updateOrgPosition(image, payload, request);
	}

	@PostMapping("/getOrgDetailsById")
	public Map<Object, Object> getOrganizationDetailsById(Long id) {
		return orgService.viewDetailsById(id);
	}

	@PostMapping("/activeDeactiveStatus")
	public Map<Object, Object> activeDeactiveStatus(Long id, int status, ServletRequest request) {
		return orgService.activeDeactiveStatus(id, status, request);
	}

	@GetMapping("/active-positions")
	public Map<Object, Object> activePositions() {
		return orgService.getAllActivePositions();
	}

	@GetMapping("/team-member-list")
	public Map<Object, Object> teamMemberList() {
		return orgService.getAllTeamMembers();
	}

	@PostMapping("/change-member-status")
	public Map<Object, Object> changeMemberStatus(@RequestParam(value = "id") Long id,
			@RequestParam(value = "status") int status) {
		return orgService.activeDeactiveMemberStatus(id, status);
	}

}
