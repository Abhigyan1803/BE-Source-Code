package com.example.demo.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CommandantDailyProgramme;
import com.example.demo.model.RespDetails;
import com.example.demo.repository.RespDetailsRepo;
import com.example.demo.service.RespService;
import com.example.demo.util.ConstantMessage;

@Service
public class RespServiceImpl implements RespService{

	@Autowired
	RespDetailsRepo respDetailsRepo;
	
	
	@Override
	public Map<Object, Object> getAllResp() {
		HashMap<Object, Object> map = new HashMap<>();
		try
		{
			List<RespDetails> respList = respDetailsRepo.findAll();
			if(respList.size() !=0)
			{
				map.put(ConstantMessage.LIST,respList);
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
