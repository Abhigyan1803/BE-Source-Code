package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.AuthTable;
import com.example.demo.model.User;
import com.example.demo.myexception.MyException;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ConstantVar;
import com.example.demo.util.FileWritting;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repo;
	@Autowired
	RoleRepository roleRepo;

	@Autowired
	LoginRepository loginRepo;

	@Override
	public User createUser(User usr) throws MyException {
		User already_user = repo.findByEmail(usr.getEmail());
		User already_service = repo.findByServiceId(usr.getServiceId());
		if (null != already_user && usr.getEmail().equals(already_user.getEmail())) {
			throw new MyException(ConstantMessage.EMAIL_EXIST);
		}
		if (null != already_service && usr.getServiceId().equals(already_user.getServiceId())) {
			throw new MyException(ConstantMessage.SERVICE_EXIST);
		}
		usr.setPassword(new BCryptPasswordEncoder().encode(usr.getPassword()));
		System.out.println("");
		usr.setRoles(roleRepo.findById(usr.getRoles().getRoleId()).get());
		usr.setIsDeleted(ConstantVar.ZER0);
		usr.setStatus(ConstantVar.ZER0);// 2may

		usr = repo.save(usr);

		if (usr != null) {
			int recordCheck = insertRecordInAuthTAble(usr);
			if (recordCheck == 1) {

				throw new MyException(ConstantMessage.USER_NOT_EXIST);
			} else if (recordCheck == 2) {
				throw new MyException(ConstantMessage.USER_NOT_ADDED);
			}
		}

		return usr;
	}

	public int insertRecordInAuthTAble(User user) throws MyException {
		try {
	//		String pwd = repo.getPassword(user.getUsername());
			if (user != null) {
				AuthTable authData = new AuthTable();
				authData.setEmail(user.getEmail().trim());
				// authData.setUsername(user.getServiceId().trim());
				authData.setUsername(user.getUsername().trim());
				authData.setHasRole("1"); //formultipleroles
				authData.setName(user.getFirstName().trim().concat(user.getLastName().trim()));
				authData.setPassword(user.getPassword());
				authData.setBattalionId(user.getBattalianId());
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
	public User getdataByUsernameAndBattalionId(String username, Integer battalionId, ServletRequest request)
			throws MyException {
		User usr = repo.findByUsernameAndBattalianId(username, battalionId);// 2may

		if (usr == null) {
			FileWritting.createLog((HttpServletRequest) request,
					username + ",user login," + "failed," + ConstantMessage.USER_NOT_EXIST + "," + new Date());
			throw new MyException(ConstantMessage.USER_NOT_EXIST);

		}
		return usr;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> list = repo.findAllByOrderByIdDesc();
		return list;
	}

	@Override
	public Optional<User> getUserById(Integer id) {
		Optional<User> list = repo.findById(id);
		return list;
	}

	@Override
	public User updateUser(User usr) {
		if (usr.getPassword() != null) {
			usr.setPassword(new BCryptPasswordEncoder().encode(usr.getPassword()));
		}
		if (usr.getRoles() != null) {
			usr.setRoles(roleRepo.findById(usr.getRoles().getRoleId()).get());
		}

		usr = repo.save(usr);
		return usr;
	}

}
