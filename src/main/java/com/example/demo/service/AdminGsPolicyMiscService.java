package com.example.demo.service;

import java.util.List;

import com.example.demo.model.GsPolicyMisc;

public interface AdminGsPolicyMiscService {

	GsPolicyMisc createMisc(GsPolicyMisc misc);

	List<GsPolicyMisc> getAllMiscList(Integer status);

	GsPolicyMisc getMiscById(Integer id);

	GsPolicyMisc updateMisc(GsPolicyMisc misc);

}
