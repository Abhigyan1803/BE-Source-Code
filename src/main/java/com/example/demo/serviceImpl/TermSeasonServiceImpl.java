package com.example.demo.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TermSeason;
import com.example.demo.repository.TermSeasonRepo;
import com.example.demo.service.TermSeasonService;
import com.example.demo.util.ConstantMessage;

@Service
public class TermSeasonServiceImpl  implements TermSeasonService{

	
	@Autowired
	TermSeasonRepo seasonRepo;
	
	@Override
	public Map<Object, Object> getAllTermSeaon() {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			List<TermSeason> termList = seasonRepo.findAll();
			if (termList != null) {
				map.put(ConstantMessage.LIST, termList);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;	
	}

}
