package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserIdAndRoleManagement;
import com.example.demo.myexception.MyException;
import com.example.demo.repository.UserIdAndRoleManagementRepo;
import com.example.demo.service.UserIdAndRoleManagementService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;

@Service
public class UserIdAndRoleManagementServiceImpl implements UserIdAndRoleManagementService {

	@Autowired
	private UserIdAndRoleManagementRepo userIdAndRoleManagementRepo;

	@Override
	public UserIdAndRoleManagement CreateUserIdAndRoleManagement(UserIdAndRoleManagement userIdAndRoleManagement) {
		// TODO Auto-generated method stub
		UserIdAndRoleManagement userIdAndRoleMngt = null;
		String pwd = new BCryptPasswordEncoder().encode(userIdAndRoleManagement.getPassword());
		userIdAndRoleManagement.setPassword(pwd);
		userIdAndRoleMngt = userIdAndRoleManagementRepo.save(userIdAndRoleManagement);
		return userIdAndRoleMngt;
	}

	@Override
	public UserIdAndRoleManagement getUserIdAndRoleMngtByRoleIdAndSubRoleIdAndAppIdAndUserId(Long roleId,
			Long subRoleId, Long appId, Long userId) {
		// TODO Auto-generated method stub
		UserIdAndRoleManagement userIdAndRoleManagement = userIdAndRoleManagementRepo
				.findAllByRoleIdAndSubRoleIdAndAppIdAndUserId(roleId, subRoleId, appId, userId);
		return userIdAndRoleManagement;
	}

	@Override
	public List<UserIdAndRoleManagement> getUserIdAndRoleManagement() {
		// TODO Auto-generated method stub
		List<UserIdAndRoleManagement> userIdAndRoleManagement = userIdAndRoleManagementRepo.findAll();
		return userIdAndRoleManagement;
	}

	@Override
	public UserIdAndRoleManagement getUserIdAndRoleMngtByUserName(String userName, ServletRequest request)
			throws MyException {
		// TODO Auto-generated method stub
		UserIdAndRoleManagement userIdAndRoleManagement = userIdAndRoleManagementRepo.findByUserName(userName);
		if (userIdAndRoleManagement == null) {
			FileWritting.createLog((HttpServletRequest) request,
					userName + ",user login," + "failed," + ConstantMessage.USER_NOT_EXIST + "," + new Date());
			throw new MyException(ConstantMessage.USER_NOT_EXIST);

		}
		return userIdAndRoleManagement;
	}

}
