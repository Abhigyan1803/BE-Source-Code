package com.example.demo.serviceImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Admin;
import com.example.demo.model.AuthTable;
import com.example.demo.model.Cadet;
import com.example.demo.model.User;
import com.example.demo.model.UserIdAndRoleManagement;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.UserIdAndRoleManagementRepo;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ConstantVar;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	LoginRepository loginRepo;

	@Autowired
	AdminRepository adminRepo;

	@Autowired
	UserRepository repo;

	@Autowired
	AdminCadetRepo cadetRepo;

	@Autowired
	private UserIdAndRoleManagementRepo userIdAndRoleManagementRepo;

	User usr = null;
	UserIdAndRoleManagement usrRolemngt = null;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserIdAndRoleManagement usrRole = userIdAndRoleManagementRepo.findByUserName(username);
		AuthTable user = loginRepo.findByUsername(username);
		if (user == null && usrRole == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		} else if (user != null && user.getHasRole() != null && user.getHasRole().equals("0")) {  //formultipleuser
			Admin adminData = adminRepo.findByUsernameAndIsDeleted(username, ConstantVar.ZER0);
			if (adminData != null) {
				return new org.springframework.security.core.userdetails.User(adminData.getUsername(),
						adminData.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
			} else {
				throw new UsernameNotFoundException(ConstantMessage.USER_NOT_EXIST);
			}
		} else if (user != null && user.getHasRole() != null && user.getHasRole().equals("1")) { //formultipleuser
			usr = repo.findByUsernameAndIsDeletedAndStatus(username, ConstantVar.ZER0, ConstantVar.ZER0);// 2 may added
			if (usr != null) {
				return new org.springframework.security.core.userdetails.User(usr.getUsername(), usr.getPassword(),
						getAuthority(usr));
			} else {
				throw new UsernameNotFoundException(ConstantMessage.USER_NOT_EXIST);
			}
		} else if (usrRole != null) {
			usrRolemngt = userIdAndRoleManagementRepo.findByUserNameAndIsDeletedAndStatus(username, ConstantVar.ONE,
					ConstantVar.ONE);
			if (usrRolemngt != null) {
				return new org.springframework.security.core.userdetails.User(usrRolemngt.getUserName(),
						usrRolemngt.getPassword(), getAuthority(usrRolemngt));
			} else {
				throw new UsernameNotFoundException(ConstantMessage.USER_NOT_EXIST);
			}
		} else if (user != null && user.getHasRole() != null && user.getHasRole().equals("3")) { //formultipleuser
			Cadet cadet = null;
			cadet = cadetRepo.findByUsernameAndStatus(username, ConstantVar.ONE);// 7 june added
			if (cadet != null) {
				return new org.springframework.security.core.userdetails.User(cadet.getUsername(), cadet.getPassword(),
						Arrays.asList(new SimpleGrantedAuthority("ROLE_CADET")));
			} else {
				throw new UsernameNotFoundException(ConstantMessage.USER_NOT_EXIST);
			}
		} else {
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
		}

	}

	private Set<SimpleGrantedAuthority> getAuthority(UserIdAndRoleManagement user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority(user.getRoleName())); // 2 may added
		return authorities;
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        user.getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
//        });

		// user.getRoles().forEach(role -> {
		authorities.add(new SimpleGrantedAuthority("ROLE_" + usr.getRoles())); // 2 may added
		// });
		return authorities;
	}
}