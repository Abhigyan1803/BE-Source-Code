package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AppointementManagement;
import com.example.demo.model.RoleManagement;
import com.example.demo.model.SubRoleManagement;
import com.example.demo.model.UserIdAndRoleManagement;
import com.example.demo.model.UserIdManagement;
import com.example.demo.myexception.MyException;
import com.example.demo.service.AppointementManagementService;
import com.example.demo.service.RoleManagementService;
import com.example.demo.service.SubRoleManagementService;
import com.example.demo.service.UserIdAndRoleManagementService;
import com.example.demo.service.UserIdManagementService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/RoleManagement")
@CrossOrigin
public class RoleManagementController {

	@Autowired
	private RoleManagementService roleManagementService;

	@Autowired
	private SubRoleManagementService subRoleManagementService;

	@Autowired
	private AppointementManagementService appointementManagementService;

	@Autowired
	private UserIdManagementService userIdManagementService;

	@Autowired
	private UserIdAndRoleManagementService userIdAndRoleManagementService;

	@PostMapping(value = "/saveRole")
	public ResponseEntity<?> addCreateRole(@RequestBody RoleManagement roleManagement) throws MyException {
		RoleManagement response = roleManagementService.createRole(roleManagement);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ROLE_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getRoles")
	public ResponseEntity<?> getRoles() throws Exception {
		List<RoleManagement> response = roleManagementService.getRolesByIsShow(1);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping(value = "/saveSubRole")
	public ResponseEntity<?> addCreateSubRole(@RequestBody SubRoleManagement subroleManagement) throws MyException {
		SubRoleManagement response = subRoleManagementService.createSubRole(subroleManagement);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.SUB_ROLE_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getSubRoles")
	public ResponseEntity<?> getSubRoles() throws Exception {
		List<SubRoleManagement> response = subRoleManagementService.getSubRoles();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getSubRolesByRoleId")
	public ResponseEntity<?> getSubRolesByRoleId(Long roleId) throws Exception {
		List<SubRoleManagement> response = subRoleManagementService.getSubRolesByRoleId(roleId);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping(value = "/saveAppointement")
	public ResponseEntity<?> addCreateAppointement(@RequestBody AppointementManagement appointementManagement)
			throws MyException {
		AppointementManagement response = appointementManagementService.CreateAppointement(appointementManagement);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.APPOINTEMENT_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getAppointement")
	public ResponseEntity<?> getAppointement() throws Exception {
		List<AppointementManagement> response = appointementManagementService.getAppointement();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getAppointementByRoleIdAndSubRoleId")
	public ResponseEntity<?> getAppointementByRoleIdAndSubRoleId(Long roleId, Long subRoleId) throws Exception {
		List<AppointementManagement> response = appointementManagementService
				.getAppointementByRoleIdAndSubRoleId(roleId, subRoleId);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping(value = "/saveUserId")
	public ResponseEntity<?> addCreateUserId(@RequestBody UserIdManagement userIdManagement) throws MyException {
		UserIdManagement response = userIdManagementService.CreateUserId(userIdManagement);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.USERID_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getUserId")
	public ResponseEntity<?> getUserId() throws Exception {
		List<UserIdManagement> response = userIdManagementService.getUserId();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getUserIdByRoleIdAndSubRoleIdAndAppId")
	public ResponseEntity<?> getUserIdByRoleIdAndSubRoleIdAndAppId(Long roleId, Long subRoleId, Long appId)
			throws Exception {
		List<UserIdManagement> response = userIdManagementService.getUserIdByRoleIdAndSubRoleIdAndAppId(roleId,
				subRoleId, appId);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping(value = "/saveUserIdAndRoleManagement")
	public ResponseEntity<?> addCreateUserIdAndRoleManagement(
			@RequestBody UserIdAndRoleManagement userIdAndRoleManagement) throws MyException {
		UserIdAndRoleManagement response = userIdAndRoleManagementService
				.CreateUserIdAndRoleManagement(userIdAndRoleManagement);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.USERID_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getUserIdAndRoleManagement")
	public ResponseEntity<?> getUserIdAndRoleManagement() throws Exception {
		List<UserIdAndRoleManagement> response = userIdAndRoleManagementService.getUserIdAndRoleManagement();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getUserIdAndRoleMngtByRoleIdAndSubRoleIdAndAppIdAndUserId")
	public ResponseEntity<?> getUserIdAndRoleMngtByRoleIdAndSubRoleIdAndAppIdAndUserId(Long roleId, Long subRoleId,
			Long appId, Long userId) throws Exception {
		UserIdAndRoleManagement response = userIdAndRoleManagementService
				.getUserIdAndRoleMngtByRoleIdAndSubRoleIdAndAppIdAndUserId(roleId, subRoleId, appId, userId);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}
}
