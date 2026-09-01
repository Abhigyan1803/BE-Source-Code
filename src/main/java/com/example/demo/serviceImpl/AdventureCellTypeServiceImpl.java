package com.example.demo.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AdventureCellType;
import com.example.demo.repository.AdventureCellTypeRepo;
import com.example.demo.service.AdventureCellTypeService;
import com.example.demo.util.ConstantMessage;

@Service
public class AdventureCellTypeServiceImpl implements AdventureCellTypeService {

	@Autowired
	AdventureCellTypeRepo acRepo;
	
	
	@Override
	public Map<Object, Object> getAllAcTypes() {
		
		HashMap<Object, Object> map = new HashMap<>();
		try
		{
			List<AdventureCellType> acTypeList =  acRepo.findAll();
			if(acTypeList != null)
			{
				map.put(ConstantMessage.LIST, acTypeList);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			}
			else
			{
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

}
