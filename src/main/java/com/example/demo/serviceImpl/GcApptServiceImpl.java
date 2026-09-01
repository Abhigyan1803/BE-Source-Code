package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.GcAppt;
import com.example.demo.repository.GcApptRepo;
import com.example.demo.service.GcApptService;

@Service
public class GcApptServiceImpl implements GcApptService {

	@Autowired
	private GcApptRepo gcApptRepo;

	@Override
	public List<GcAppt> getGcAppt() {
		// TODO Auto-generated method stub
		return gcApptRepo.findAll();
	}

}
