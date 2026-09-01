package com.example.demo.serviceImpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ApptType;
import com.example.demo.model.Battalion;
import com.example.demo.repository.ApptTypeRepo;
import com.example.demo.service.ApptTypeService;
import com.example.demo.util.ConstantVar;

@Service
public class ApptTypeServiceImpl implements ApptTypeService{

	@Autowired
	ApptTypeRepo apptTypeRepo; 
	
	@Override
	public List<ApptType> getAllApptList() {
	List<ApptType> list = apptTypeRepo.findAll();
		return list;
	}

}
