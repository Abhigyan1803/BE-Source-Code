package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Admin;
import com.example.demo.model.AuthTable;
import com.example.demo.model.Battalion;
import com.example.demo.model.BattalionCompany;
import com.example.demo.model.Officer;
import com.example.demo.model.RoleEntity;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.AuthTablePayLoad;
import com.example.demo.payload.RolePayload;
import com.example.demo.repository.AdminBattalionRepo;
import com.example.demo.repository.AdminRecordOfServiceRepo;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.BattalionCompanyRepo;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.RoleEntityRepo;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.AdminService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ConstantVar;

@Service
public class AdminServiceImpl implements AdminService {

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	@Autowired
	RoleRepository roleRepo;

	@Autowired
	AdminRepository adminRepo;
	@Autowired
	LoginRepository loginRepo;
	@Autowired
	AdminRecordOfServiceRepo recordOfServiceRepo;

	@Autowired
	RoleEntityRepo roleEntityRepo;

	@Autowired
	AdminBattalionRepo adminBattalionRepo;

	@Autowired
	BattalionCompanyRepo companyRepo;

	@Override
	public Admin getdataByUsername(String username) throws MyException {
		Admin usr = null;
		try {
			usr = adminRepo.findByUsernameAndIsDeleted(username, ConstantVar.ZER0);
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyException(ConstantMessage.ERROR_MESSAGE, e);
		}

		return usr;
	}

	@Override
	public Admin createAdmins(Admin admin) throws MyException {
		try {
			AuthTable auth = loginRepo.findByUsername(admin.getUsername());
			if (auth == null) {
				Admin admin1 = new Admin();
				admin1.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
				admin1.setIsDeleted(ConstantVar.ZER0);
				admin1.setEmail(admin.getEmail());
				admin1.setIsDeleted(ConstantVar.ZER0);
				admin1.setName(admin.getName());
				admin1.setServiceId(admin.getServiceId());
				admin1.setUsername(admin.getUsername());
//				admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
//				admin.setIsDeleted(ConstantVar.ZER0);
				admin1 = adminRepo.save(admin1);
				if (admin1 != null) {
					int recordCheck = insertRecordInAuthTAble(admin);
					if (recordCheck == 1) {
						throw new MyException(ConstantMessage.USER_NOT_EXIST);
					} else if (recordCheck == 2) {
						throw new MyException(ConstantMessage.USER_NOT_ADDED);
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new MyException(ConstantMessage.ERROR_MESSAGE, e);
		}

		return admin;
	}

	public int insertRecordInAuthTAble(Admin admin) throws MyException {
		try {
			String pwd = adminRepo.getPassword(admin.getUsername());
			if (pwd != null) {
				AuthTable authData = new AuthTable();
				if (admin.getEmail() != null) {
					authData.setEmail(admin.getEmail().trim());
				}
				authData.setUsername(admin.getUsername().trim());
				authData.setHasRole("0");
				if (admin.getName() != null) {
					authData.setName(admin.getName().trim());
				}
				authData.setPassword(pwd);
				authData = loginRepo.save(authData);
				if (authData == null) {
					// return 2 if data not inserted in login table
					return 2;
				}

			} else {
				// if admin record not found and data not inserted in login table
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// return 0 if succcess , not required to handle this code above

		return 0;

	}

	@Override
	public List<Admin> getAllAdmin() {
		List<Admin> adminList = adminRepo.findAll();
		if (adminList.size() > 0) {
			return adminList;
		}
		return null;
	}

	@Override
	public String createAdminsNew(Admin admin) {
		if (admin.getUsername() == null || admin.getUsername().trim().equals("")) {
			return "username empty";
		}
		if (admin.getPassword() == null || admin.getPassword().trim().equals("")) {
			return "password empty";
		}
		AuthTable auth = loginRepo.findByUsername(admin.getUsername());
		if (auth == null) {
			Admin admin1 = new Admin();
			String pwd = new BCryptPasswordEncoder().encode(admin.getPassword());
			admin1.setPassword(pwd);
			admin1.setIsDeleted(ConstantVar.ZER0);
			admin1.setEmail(admin.getEmail());
			admin1.setIsDeleted(ConstantVar.ZER0);
			admin1.setName(admin.getName());
			admin1.setServiceId(admin.getServiceId());
			admin1.setUsername(admin.getUsername());
			admin1 = adminRepo.save(admin1);
			if (admin1 != null) {
				AuthTable authData = new AuthTable();
				authData.setUserId(admin1.getAdminId());
				if (admin.getEmail() != null) {
					authData.setEmail(admin.getEmail().trim());
				}
				authData.setUsername(admin.getUsername().trim());
				authData.setHasRole("0");
				if (admin.getName() != null) {
					authData.setName(admin.getName().trim());
				}
				authData.setPassword(pwd);
				authData = loginRepo.save(authData);
				if (authData != null) {
					return ConstantMessage.USER_ADDED;
				}
				return ConstantMessage.FAILED_TO_ADD;
			} else {
				return ConstantMessage.FAILED_TO_ADD;
			}
		} else {
			return ConstantMessage.RECORD_ALREADY_EXIST;
		}

	}

	@Override
	public Admin updateAdminStatus(Admin admin) {
		Admin adn = null;
		Optional<Admin> adm = adminRepo.findByAdminId(admin.getAdminId());
		if (adm.isPresent()) {
			adn = adm.get();
			if (admin.getStatus() != null) {
				adn.setStatus(admin.getStatus());
				adn = adminRepo.save(adn);
			}
		}
		return adn;
	}

	@Override
	public String updateAdminPassward(Admin admin) {
		if (admin == null || admin.getAdminId() == 0 || admin.getPassword() == null
				|| admin.getPassword().trim().equals("")) {
			return "Invalid data";
		}

		Optional<Admin> adminResult = adminRepo.findByAdminId(admin.getAdminId());
		if (adminResult.isPresent()) {
			Admin admin1 = adminResult.get();
			String pwd = new BCryptPasswordEncoder().encode(admin.getPassword());
			admin1.setPassword(pwd);
			admin1 = adminRepo.save(admin1);

			AuthTable auth = loginRepo.findByUsername(admin1.getUsername());
			auth.setPassword(pwd);
			auth = loginRepo.save(auth);

			if (admin1 != null && auth != null) {
				return "success";
			}

		}
		return "Someting went wrong";
	}

	@Override
	public String addStaff(AuthTable user) {
		if (user == null) {
			return "Invalid Request";
		}
		if (user.getHasRole() == null) {
			return "Role empty";
		}
		if (user.getUserId() == null) {
			return "UserId empty";
		}
		if (user.getUsername() == null || user.getUsername().trim().equals("")) {
			return "Username empty";
		}
		if (user.getPassword() == null || user.getPassword().trim().equals("")) {
			return "Password empty";
		}
		AuthTable auth = loginRepo.findByUsername(user.getUsername());
		if (auth == null) {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			user = loginRepo.save(user);
			if (user != null) {
				return ConstantMessage.USER_ADDED;
			}
			return ConstantMessage.FAILED_TO_ADD;
		} else {
			return ConstantMessage.RECORD_ALREADY_EXIST;
		}
	}

	@Override
	public List<AuthTablePayLoad> getStaffs(Integer status) {
		List<AuthTable> list = null;
		if (status == 1) {
			list = loginRepo.getActiveStaffs();
		} else {
			list = loginRepo.getStaffs();
		}

		if (list.size() > 0) {
			List<AuthTablePayLoad> authPayLoadList = new ArrayList<AuthTablePayLoad>();
			for (AuthTable auth : list) {
				AuthTablePayLoad authPayLoad = new AuthTablePayLoad();
				authPayLoad.setLoginId(auth.getLoginId());
				authPayLoad.setUserId(auth.getUserId());
				authPayLoad.setCompId(auth.getCompId());
				authPayLoad.setBattalionId(auth.getBattalionId());
				authPayLoad.setHasRole(auth.getHasRole());
				authPayLoad.setStatus(auth.getStatus());
				authPayLoad.setUsername(auth.getUsername());
				if (auth.getUserId() != null && auth.getUserId() != 0) {
					Optional<Officer> officerResult = recordOfServiceRepo.findById(auth.getUserId());
					if (officerResult.isPresent()) {
						Officer officer = officerResult.get();
						authPayLoad.setName(officer.getName());
					}
				}
				///
				String roleString =  auth.getHasRole();
				String[] roleArr = roleString.split(",");
				List<RoleEntity> RoleEntityList = new ArrayList<RoleEntity>();
				for(String roleId:roleArr) {
                    
					Optional<RoleEntity> roleEntityResult = roleEntityRepo
							.findById(Long.parseLong(roleId));
					if (roleEntityResult.isPresent()) {
						RoleEntity roleEntity = roleEntityResult.get();
						authPayLoad.setRoleName(authPayLoad.getRoleName()==null?""+roleEntity.getRoleName():authPayLoad.getRoleName()+","+roleEntity.getRoleName());
						RoleEntityList.add(roleEntity);
					}
					
				}
				//Optional<RoleEntity> roleEntityResult = roleEntityRepo
				//		.findById(Long.parseLong(auth.getHasRole().toString()));
				
//				if (roleEntityResult.isPresent()) {
//					RoleEntity roleEntity = roleEntityResult.get();
//					authPayLoad.setRoleName(roleEntity.getRoleName());
//				}
				if (auth.getBattalionId() != null && auth.getBattalionId() != 0) {
					Optional<Battalion> battionResult = adminBattalionRepo.findById(auth.getBattalionId());
					if (battionResult.isPresent()) {
						Battalion battalion = battionResult.get();
						authPayLoad.setBattalionName(battalion.getShortName());
					}
				}
				if (auth.getCompId() != null && auth.getCompId() != 0) {
					Optional<BattalionCompany> companyResult = companyRepo.findById(auth.getCompId());
					if (companyResult.isPresent()) {
						BattalionCompany company = companyResult.get();
						authPayLoad.setCompanyName(company.getName());
					}
				}
				authPayLoadList.add(authPayLoad);
			}
			return authPayLoadList;
		}
		return null;
	}

	@Override
	public String changeStaffStatus(AuthTable user) {
		if (user != null && user.getLoginId() != 0) {
			AuthTable auth = loginRepo.findByLoginId(user.getLoginId());
			if (auth != null) {
				if (user.getStatus() != null) {
					auth.setStatus(user.getStatus());
					loginRepo.save(auth);
					return ConstantMessage.RECORD_UPDATED;
				}
			}
			return ConstantMessage.FAILED_TO_UPDATE;
		} else {
			return ConstantMessage.RECORD_NOT_FOUND;
		}
	}

	@Override
	public String updateStaff(AuthTable user) {
		if (user == null || user.getLoginId() == 0) {
			return "Invalid Request";
		}
		if (user.getHasRole() == null) {
			return "Role empty";
		}
		if (user.getUserId() == null) {
			return "UserId empty";
		}
		if (user.getUsername() == null || user.getUsername().trim().equals("")) {
			return "Username empty";
		}
		if (user.getPassword() == null || user.getPassword().trim().equals("")) {
			return "Password empty";
		}
		AuthTable auth = loginRepo.findByUsername(user.getUsername());
		if (auth == null || auth.getLoginId() == user.getLoginId()) {
			auth = loginRepo.findByLoginId(user.getLoginId());
			if (auth != null) {
				auth.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
				auth.setUsername(user.getUsername());
				auth.setUserId(user.getUserId());
				auth.setBattalionId(user.getBattalionId());
				auth.setCompId(user.getCompId());
				auth.setHasRole(user.getHasRole());
				user = loginRepo.save(auth);
				if (user != null) {
					return ConstantMessage.USER_UPDATED;
				}
				return ConstantMessage.FAILED_TO_UPDATE;
			} else {
				return ConstantMessage.RECORD_NOT_FOUND;
			}

		} else {
			return ConstantMessage.USERNAME_ALREADY_EXIST;
		}
	}

}
