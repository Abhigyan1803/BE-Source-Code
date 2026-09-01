package com.example.demo.logincontroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.model.Admin;
import com.example.demo.model.AuthTable;
import com.example.demo.model.AuthToken;
import com.example.demo.model.Battalion;
import com.example.demo.model.BattalionCompany;
import com.example.demo.model.Cadet;
import com.example.demo.model.Officer;
import com.example.demo.model.User;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.AuthTablePayLoad;
import com.example.demo.payload.CadetLoginPayload;
import com.example.demo.payload.RolePayload;
import com.example.demo.payload.StaffLoginPayLoad;
import com.example.demo.repository.AdminBattalionRepo;
import com.example.demo.repository.BattalionCompanyRepo;
import com.example.demo.repository.LoginRepository;
import com.example.demo.service.AdminBattalionService;
import com.example.demo.service.AdminCadetService;
import com.example.demo.service.AdminRecordOfService;
import com.example.demo.service.AdminService;
import com.example.demo.service.UserRoleModuleService;
import com.example.demo.service.UserService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userSrv;

	@Autowired
	private AdminService adminSrv;

	@Autowired
	LoginRepository loginRepo;

	@Autowired
	AdminCadetService cadetService;

	@Autowired
	AdminBattalionService battalionService;
	@Autowired
	BattalionCompanyRepo btCompanyRepo;

	@Autowired
	AdminRecordOfService recordOfService;

	@Autowired
	UserRoleModuleService roleModuleService;

	@Autowired
	AdminBattalionRepo battalionRepo;

	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public ResponseEntity<?> generateTokenForAdmin(@RequestBody AuthTable loginUser, ServletRequest request)
			throws AuthenticationException, MyException {

		// if needed to login with email then add this line
		// loginUser.setUsername(loginUser.getEmail());
		// System.out.println(loginUser.getUsername() + "this is" +
		// loginUser.getPassword());
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginUser.getUsername().trim(), loginUser.getPassword().trim()));
		} catch (BadCredentialsException e) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.INVALID_CREDENTIALS, HttpStatus.OK, null),
					HttpStatus.OK);
		}
//		final Authentication authentication = authenticationManager
//				.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername().trim(),
//						loginUser.getPassword().trim()));
		Admin userdata = null;
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtTokenUtil.generateToken(authentication);
		AuthTable authUser = loginRepo.findByUsername(loginUser.getUsername().trim());
		if (authUser.getHasRole().equals("0")) {   //formultipleroles
			if (StringUtils.isNotEmpty(token)) {
				userdata = adminSrv.getdataByUsername(loginUser.getUsername().trim());
				if (userdata == null) {
					FileWritting.createLog((HttpServletRequest) request, loginUser.getUsername() + ",admin login,"
							+ "failed," + "admin not found" + "," + new Date());
					return new ResponseEntity<>(
							new ResponseMessage(ConstantMessage.USER_NOT_EXIST, HttpStatus.OK, new AuthToken(token)),
							HttpStatus.OK);

				}
				if (userdata.getStatus() == null || userdata.getStatus() == 0) {
					FileWritting.createLog((HttpServletRequest) request, loginUser.getUsername() + ",admin login,"
							+ "failed," + "admin deactivated" + "," + new Date());
					return new ResponseEntity<>(
							new ResponseMessage(ConstantMessage.ACCOUNT_DEACTIVATED, HttpStatus.OK, null),
							HttpStatus.OK);
				}
			}
			FileWritting.createLog((HttpServletRequest) request,
					loginUser.getUsername() + ",admin login," + "success," + "login success" + "," + new Date());
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.SUCCESS_MESSAGE, HttpStatus.OK, userdata, new AuthToken(token)),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(
					new ResponseMessage("Invalid Admin ...!!", HttpStatus.OK, null, new AuthToken(token)),
					HttpStatus.OK);
		}

	}

//	@RequestMapping(value = "/admin/addAdmin", method = RequestMethod.POST)
//	public ResponseEntity<?> adminadded(@RequestBody Admin admin) throws MyException {
//
//		Admin response = adminSrv.createAdmins(admin);
//		// return new ResponseEntity<ResponseMessage>( new
//		// ResponseMessage(ConstantMessage.SUCCESS_MESSAGE,
//		// HttpStatus.OK, response)), HttpStatus.OK);
//		//
//		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.USER_ADDED, HttpStatus.OK, response),
//				HttpStatus.OK);
//	}

	@RequestMapping(value = "/admin/addAdminNew", method = RequestMethod.POST)
	public ResponseEntity<?> adminaddedNew(@RequestBody Admin admin) {
		String response = adminSrv.createAdminsNew(admin);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody AuthTable loginUser, ServletRequest request)
			throws AuthenticationException, MyException {
		// loginUser.setUsername(loginUser.getEmail());
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginUser.getUsername().trim(), loginUser.getPassword().trim()));
		} catch (BadCredentialsException e) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.INVALID_CREDENTIALS, HttpStatus.OK, null),
					HttpStatus.OK);
		}
//		final Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(loginUser.getUsername().trim(), loginUser.getPassword().trim() // 2
//																														// may
//																														// added
//				));
		User userdata = null;
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtTokenUtil.generateToken(authentication);

		AuthTable authUser = loginRepo.findByUsername(loginUser.getUsername().trim());
//		if (authUser.getHasRole() == 3) {
//			Cadet cadetdata = cadetService.getDataByUsernameAndBattalian(authUser.getUsername(),
//					authUser.getBattalionId(), request);
//			FileWritting.createLog((HttpServletRequest) request,
//					loginUser.getUsername() + ",cadet login," + "success," + "login success" + "," + new Date());
//			return new ResponseEntity<>(
//					new ResponseMessage(" Cadet detail ...!!", HttpStatus.OK, cadetdata, new AuthToken(token)),
//					HttpStatus.OK);
//		}
		if (!authUser.getHasRole().equals("0") && !authUser.getHasRole().equals("3")) { //formultipleroles
			if (StringUtils.isNotEmpty(token)) {
				userdata = userSrv.getdataByUsernameAndBattalionId(loginUser.getUsername().trim(),
						authUser.getBattalionId(), request);// 2 may added
				FileWritting.createLog((HttpServletRequest) request,
						loginUser.getUsername() + ",user login," + "success," + "login success" + "," + new Date());
			}

			return new ResponseEntity<>(
					new ResponseMessage(" User detail ...!!", HttpStatus.OK, userdata, new AuthToken(token)),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(
					new ResponseMessage("Invalid User ...!!", HttpStatus.OK, null, new AuthToken(token)),
					HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/staff/login", method = RequestMethod.POST)
	public ResponseEntity<?> generateStaffToken(@RequestBody AuthTable loginUser, ServletRequest request)
			throws AuthenticationException, MyException {
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginUser.getUsername().trim(), loginUser.getPassword().trim()));
		} catch (BadCredentialsException e) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.INVALID_CREDENTIALS, HttpStatus.OK, null),
					HttpStatus.OK);
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtTokenUtil.generateToken(authentication);

		AuthTable authUser = loginRepo.findByUsername(loginUser.getUsername().trim());
		// for officer login
		if (!authUser.getHasRole().equals("0") && !authUser.getHasRole().equals("3")) { //formultipleroles
			if (StringUtils.isNotEmpty(token)) {
				Officer officer = recordOfService.getOfficerById(authUser.getUserId());
				if (officer != null) {
					if (authUser.getStatus() == 1 && officer.getStatus() == 1) {
						///
						String roleString =  authUser.getHasRole();
						String[] roleArr = roleString.split(",");
						List<RolePayload> rolePayloadList = new ArrayList<RolePayload>();
						for(String roleId:roleArr) {
                     
							RolePayload roleModuleMapping = roleModuleService
									.getRoleModuleMappingByRoleId(Long.parseLong(roleId));
							rolePayloadList.add(roleModuleMapping);
						}
						///
//						RolePayload roleModuleMapping = roleModuleService
//								.getRoleModuleMappingByRoleId(Long.parseLong(authUser.getHasRole().toString()));
						StaffLoginPayLoad loginStaff = new StaffLoginPayLoad();
						loginStaff.setLoginId(authUser.getLoginId());
						loginStaff.setUserId(authUser.getUserId());
						loginStaff.setHasRole(authUser.getHasRole());
						loginStaff.setName(officer.getName());
						loginStaff.setUsername(authUser.getUsername());

						Battalion battalion = null;
						if (authUser.getBattalionId() != null) {
							Optional<Battalion> battalionOpt = battalionRepo.findById(authUser.getBattalionId());
							if (battalionOpt.isPresent()) {
								battalion = battalionOpt.get();
							}
						}

						BattalionCompany battalionCompany = null;
						if (authUser.getCompId() != null) {
							Optional<BattalionCompany> battalionCompanyOpt = btCompanyRepo
									.findById(authUser.getCompId());
							if (battalionCompanyOpt.isPresent()) {
								battalionCompany = battalionCompanyOpt.get();
							}
						}

						loginStaff.setBattalion(battalion);
						loginStaff.setCompany(battalionCompany);

						loginStaff.setModuleList(rolePayloadList);
						return new ResponseEntity<>(new ResponseMessage(" Staff detail ...!!", HttpStatus.OK,
								loginStaff, new AuthToken(token)), HttpStatus.OK);
					} else if (officer.getStatus() == 2) {
						return new ResponseEntity<>(
								new ResponseMessage(ConstantMessage.USER_NOT_EXIST, HttpStatus.OK, null, null),
								HttpStatus.OK);
					} else {
						return new ResponseEntity<>(
								new ResponseMessage(ConstantMessage.ACCOUNT_DEACTIVATED, HttpStatus.OK, null),
								HttpStatus.OK);
					}

				} else {
					return new ResponseEntity<>(
							new ResponseMessage(ConstantMessage.USER_NOT_EXIST, HttpStatus.OK, null, null),
							HttpStatus.OK);
				}
			} else {
				return new ResponseEntity<>(
						new ResponseMessage(ConstantMessage.FAILED_TO_GENERATE_TOKEN, HttpStatus.OK, null, null),
						HttpStatus.OK);
			}

		} else if (authUser.getHasRole().equals("0")) { //formultipleroles
			if (StringUtils.isNotEmpty(token)) {
				Admin adminData = adminSrv.getdataByUsername(loginUser.getUsername().trim());
				if (adminData == null || adminData.getStatus() == 2) {
					return new ResponseEntity<>(
							new ResponseMessage(ConstantMessage.USER_NOT_EXIST, HttpStatus.OK, new AuthToken(token)),
							HttpStatus.OK);
				}
				if (adminData.getStatus() == null || adminData.getStatus() == 0) {
					return new ResponseEntity<>(
							new ResponseMessage(ConstantMessage.ACCOUNT_DEACTIVATED, HttpStatus.OK, null),
							HttpStatus.OK);
				}

				StaffLoginPayLoad loginStaff = new StaffLoginPayLoad();
				loginStaff.setLoginId(authUser.getLoginId());
				loginStaff.setUserId(authUser.getUserId());
				loginStaff.setHasRole(authUser.getHasRole());
				loginStaff.setName(adminData.getName());
				loginStaff.setUsername(authUser.getUsername());
				return new ResponseEntity<>(new ResponseMessage(ConstantMessage.SUCCESS_MESSAGE, HttpStatus.OK,
						loginStaff, new AuthToken(token)), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(
						new ResponseMessage(ConstantMessage.FAILED_TO_GENERATE_TOKEN, HttpStatus.OK, null, null),
						HttpStatus.OK);
			}

		} else {
			return new ResponseEntity<>(
					new ResponseMessage("Invalid Staff ...!!", HttpStatus.OK, null, new AuthToken(token)),
					HttpStatus.OK);
		}

	}

//	@RequestMapping(value = "/AllUser/login", method = RequestMethod.POST)
//	public ResponseEntity<?> generateTokenForAllUser(@RequestBody UserIdAndRoleManagement userIdAndRoleManagement,
//			ServletRequest request) throws AuthenticationException, MyException {
//
//		final Authentication authentication = authenticationManager
//				.authenticate(new UsernamePasswordAuthenticationToken(userIdAndRoleManagement.getUserName().trim(),
//						userIdAndRoleManagement.getPassword().trim()));
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		final String token = jwtTokenUtil.generateToken(authentication);
//		UserIdAndRoleManagement userIdAndRoleMngt = null;
//		if (StringUtils.isNotEmpty(token)) {
//			userIdAndRoleMngt = userIdAndRoleManagementService
//					.getUserIdAndRoleMngtByUserName(userIdAndRoleManagement.getUserName().trim(), request);
//			FileWritting.createLog((HttpServletRequest) request, userIdAndRoleManagement.getUserName() + ",user login,"
//					+ "success," + "login success" + "," + new Date());
//		}
//
//		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.SUCCESS_MESSAGE, HttpStatus.OK,
//				userIdAndRoleMngt, new AuthToken(token)), HttpStatus.OK);
//	}

	@RequestMapping(value = "/cadet/login", method = RequestMethod.POST)
	public ResponseEntity<?> cadetLogin(@RequestBody AuthTable loginUser, ServletRequest request)
			throws AuthenticationException, MyException {
		// loginUser.setUsername(loginUser.getEmail());
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginUser.getUsername().trim(), loginUser.getPassword().trim()));
		} catch (BadCredentialsException e) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.INVALID_CREDENTIALS, HttpStatus.OK, null),
					HttpStatus.OK);
		}
//		final Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(loginUser.getUsername().trim(), loginUser.getPassword().trim() // 2
//																														// may
//																														// added
//				));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtTokenUtil.generateToken(authentication);

		AuthTable authUser = loginRepo.findByUsername(loginUser.getUsername().trim());
		if (authUser.getHasRole().equals("3")) { //formultipleroles
			Cadet cadetdata = cadetService.getDataByUsernameAndBattalian(authUser.getUsername(),
					authUser.getBattalionId(), request);
			if (cadetdata != null) {
				Battalion battalion = battalionService.getBattalionByShortName(cadetdata.getBattalian());
				BattalionCompany battalionCompany = btCompanyRepo.getCompanyByName(cadetdata.getCompany());
				CadetLoginPayload cadedResponse = new CadetLoginPayload();
				cadedResponse.setId(cadetdata.getId());
				cadedResponse.setServiceId(cadetdata.getServiceId());
				cadedResponse.setUsername(cadetdata.getUsername());
				cadedResponse.setSerialNo(cadetdata.getSerialNo());
				cadedResponse.setBattalian(battalion);
				cadedResponse.setCompany(battalionCompany);
				cadedResponse.setTermSession(cadetdata.getTermSession());
				cadedResponse.setTerm(cadetdata.getTerm());
				cadedResponse.setTermName(cadetdata.getTermName());
				cadedResponse.setName(cadetdata.getName());
				cadedResponse.setProfileImg(cadetdata.getProfileImg());
				cadedResponse.setYear(cadetdata.getYear());
				cadedResponse.setHasRole(authUser.getHasRole()); //Akash 08/08/2023 V1
				FileWritting.createLog((HttpServletRequest) request,
						loginUser.getUsername() + ",cadet login," + "success," + "login success" + "," + new Date());
				return new ResponseEntity<>(
						new ResponseMessage(" Cadet detail ...!!", HttpStatus.OK, cadedResponse, new AuthToken(token)),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>(
						new ResponseMessage("Cadet not exist ...!!", HttpStatus.OK, null, new AuthToken(token)),
						HttpStatus.OK);
			}

		} else {
			return new ResponseEntity<>(
					new ResponseMessage("Invalid Cadet ...!!", HttpStatus.OK, null, new AuthToken(token)),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/edossier/login", method = RequestMethod.POST)
	public ResponseEntity<?> edossierLogin(@RequestBody AuthTable loginUser, ServletRequest request)
			throws AuthenticationException, MyException {
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginUser.getUsername().trim(), loginUser.getPassword().trim()));
		} catch (BadCredentialsException e) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.INVALID_CREDENTIALS, HttpStatus.OK, null),
					HttpStatus.OK);
		}
//		final Authentication authentication = authenticationManager
//				.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername().trim(),
//						loginUser.getPassword().trim()));
		User userdata = null;
		Admin adminData = null;
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtTokenUtil.generateToken(authentication);

		AuthTable authUser = loginRepo.findByUsername(loginUser.getUsername().trim());
		if (authUser.getHasRole().equals("0")) { //formultipleroles
			adminData = adminSrv.getdataByUsername(loginUser.getUsername().trim());
			if (adminData == null) {
				FileWritting.createLog((HttpServletRequest) request,
						loginUser.getUsername() + ",admin login," + "failed," + "admin not found" + "," + new Date());
				return new ResponseEntity<>(
						new ResponseMessage(ConstantMessage.USER_NOT_EXIST, HttpStatus.OK, new AuthToken(token)),
						HttpStatus.OK);

			} else {
				adminData.setRoleId(0);
				FileWritting.createLog((HttpServletRequest) request,
						loginUser.getUsername() + ",admin login," + "success," + "login success" + "," + new Date());
				return new ResponseEntity<>(new ResponseMessage(ConstantMessage.SUCCESS_MESSAGE, HttpStatus.OK,
						adminData, new AuthToken(token)), HttpStatus.OK);
			}
		} 
//		else if (authUser.getHasRole() == 1) {
//			if (StringUtils.isNotEmpty(token)) {
//				userdata = userSrv.getdataByUsernameAndBattalionId(loginUser.getUsername().trim(),
//						authUser.getBattalionId(), request);
//				FileWritting.createLog((HttpServletRequest) request,
//						loginUser.getUsername() + ",user login," + "success," + "login success" + "," + new Date());
//			}
//
//			return new ResponseEntity<>(
//					new ResponseMessage(" User detail ...!!", HttpStatus.OK, userdata, new AuthToken(token)),
//					HttpStatus.OK);
//		} 
		else {
			return new ResponseEntity<>(
					new ResponseMessage("Invalid user ...!!", HttpStatus.OK, null, new AuthToken(token)),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/admin/get_all_admin_list")
	public ResponseEntity<?> getAllAdmin() throws MyException {
		List<Admin> response = adminSrv.getAllAdmin();
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}

	@PostMapping(value = "admin/update_admin_status")
	public ResponseEntity<?> upadateAdminStatus(@RequestBody Admin admin) throws MyException {
		Admin response = adminSrv.updateAdminStatus(admin);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}

	@PutMapping(value = "/admin/update_admin_passward")
	public ResponseEntity<?> updateAdminPassword(@RequestBody Admin admin) throws MyException {
		String response = adminSrv.updateAdminPassward(admin);
		if (response.equalsIgnoreCase("success")) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.PASSWORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@PostMapping(value = "/admin/create_staff")
	public ResponseEntity<?> createStaff(@RequestBody AuthTable user, ServletRequest request) {
		String response = adminSrv.addStaff(user);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping(value = "/admin/update_staff")
	public ResponseEntity<?> updateStaff(@RequestBody AuthTable user, ServletRequest request) {
		String response = adminSrv.updateStaff(user);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/admin/get_staff_list")
	public ResponseEntity<?> getStaffs(@RequestParam(required = false) Integer status, ServletRequest request) {
		List<AuthTablePayLoad> response = adminSrv.getStaffs(status);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@PostMapping(value = "/admin/update_staff_status")
	public ResponseEntity<?> changeStaffStatus(@RequestBody AuthTable user, ServletRequest request) {
		String response = adminSrv.changeStaffStatus(user);
		return new ResponseEntity<>(new ResponseMessage(response, HttpStatus.OK, null), HttpStatus.OK);
	}

}