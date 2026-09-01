package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Position;
import com.example.demo.repository.PositionRepo;
import com.example.demo.service.PositionService;

@Service
public class PositionServiceImpl implements PositionService {
	
	@Autowired
	PositionRepo positionRepo;

	@Override
	public List<Position> getAllPositions() {
		List<Position> list = positionRepo.findAll();
		return list;
	}

}
