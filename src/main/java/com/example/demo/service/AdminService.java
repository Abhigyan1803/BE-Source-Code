package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Admin;
import com.example.demo.model.AuthTable;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.AuthTablePayLoad;

public interface AdminService {

	Admin getdataByUsername(String username) throws MyException;

	Admin createAdmins(Admin admin) throws MyException;

	List<Admin> getAllAdmin();

	String createAdminsNew(Admin admin);

	Admin updateAdminStatus(Admin admin);

	String updateAdminPassward(Admin admin);

	String addStaff(AuthTable user);

	List<AuthTablePayLoad> getStaffs(Integer status);

	String changeStaffStatus(AuthTable user);

	String updateStaff(AuthTable user);

}
