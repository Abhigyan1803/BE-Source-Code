package com.example.demo.service;

import java.util.List;

import com.example.demo.model.AppointementManagement;

public interface AppointementManagementService {

	AppointementManagement CreateAppointement(AppointementManagement appointementManagement);

	List<AppointementManagement> getAppointement();

	List<AppointementManagement> getAppointementByRoleIdAndSubRoleId(Long roleId, Long subRoleId);

}
