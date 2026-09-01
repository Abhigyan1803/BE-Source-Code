package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserRoleEntity;
import com.example.demo.model.UserRoleModel;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.RolePayload;
import com.example.demo.service.UserRoleModuleService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/userrole")
@CrossOrigin
public class LMSRoleModuleController {

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	UserRoleModuleService roleModuleService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createRole(@RequestBody UserRoleModel userRoleModel) throws MyException {
		logger.info("Create User Role: ");
		logger.debug(" LMSRoleModuleController  :  createRole() userRoleModel:- " + userRoleModel.toString());
		UserRoleModel response = roleModuleService.createRole(userRoleModel);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ROLE_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/add_role", method = RequestMethod.POST)
	public ResponseEntity<?> addRole(@RequestBody RolePayload rolePayLoad) throws MyException {
		RolePayload response = roleModuleService.addRole(rolePayLoad);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ROLE_ADDED, HttpStatus.OK, null),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_ADD, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/update_role", method = RequestMethod.POST)
	public ResponseEntity<?> updateRole(@RequestBody RolePayload rolePayLoad) throws MyException {
		RolePayload response = roleModuleService.updateUserRole(rolePayLoad);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ROLE_UPDATED, HttpStatus.OK, null),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_UPDATE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get_all_roles")
	public ResponseEntity<?> getAllRoles() throws MyException {
		List<RolePayload> response = roleModuleService.getAllRoles();
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_role_module_mapping_by_role_id")
	public ResponseEntity<?> getRoleById(@RequestParam Long roleId) {
		RolePayload response = roleModuleService.getRoleModuleMappingByRoleId(roleId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> updateRole(@RequestBody UserRoleModel roleModel) throws MyException {
		Integer response = roleModuleService.updateRole(roleModel);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ROLE_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getRoles() throws Exception {
		List<UserRoleEntity> response = roleModuleService.getRoles();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getRolesById(@RequestParam(required = true) Long id) throws Exception {
		UserRoleModel response = roleModuleService.getRolesById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{rolename}")
	public ResponseEntity<?> getRolesByRoleName(@RequestParam(required = true) String rolename) throws Exception {
		UserRoleModel response = roleModuleService.getRolesByRoleName(rolename);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteRole(@RequestParam(required = true) Long id) throws Exception {
		roleModuleService.deleteRole(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, "Deleted successfully"), HttpStatus.OK);
	}

}
