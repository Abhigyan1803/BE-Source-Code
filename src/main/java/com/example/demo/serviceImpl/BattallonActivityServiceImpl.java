package com.example.demo.serviceImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.BattallionActivity;
import com.example.demo.model.IMAActivities;
import com.example.demo.repository.BattalionActivityRepo;
import com.example.demo.service.BattallionActitvityService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.DateUtil;
import com.example.demo.util.FileWritting;

@Service
public class BattallonActivityServiceImpl implements BattallionActitvityService{

	
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;
	
	
	@Autowired
	BattalionActivityRepo battalionActivityRepo;
	
	@Override
	public Map<Object, Object> addBattallionActivity(MultipartFile img,Integer battalionId,ServletRequest servletRequest) {
		
		long time_show = System.currentTimeMillis();
		HashMap<Object, Object> map = new HashMap<>();
	   BattallionActivity  battallionActivity=new BattallionActivity();
		
		try
		{
			if(img != null && !img.isEmpty()) {
		
				byte[] bytes = img.getBytes();
	            Path path = Paths.get(UploadDir +time_show+img.getOriginalFilename().replaceAll("\\s+", "_"));
	            Files.write(path,bytes);
	            battallionActivity.setImage(url +time_show+img.getOriginalFilename());
			}
		
			battallionActivity.setBattalionId(battalionId);
			battallionActivity	= battalionActivityRepo.save(battallionActivity);
			
			if(battallionActivity != null)
			{
				FileWritting.createLog((HttpServletRequest) servletRequest,battallionActivity.getId() + ",add," + "battallionActivityAward," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.OBJECT_DETAILS, battallionActivity);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_ADDED_SUCCESSFULLY);
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
	
	
	@Override
	public Map<Object, Object> getBattalionActivityByStatus(int status) {
		HashMap<Object, Object> map = new HashMap<>();
		try
		{
			List<BattallionActivity> activityList= battalionActivityRepo.findByStatusOrderByIdDesc(status);
			if(activityList.size()!=0)
			{
				map.put(ConstantMessage.LIST, activityList);
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
		

	@Override
	public Map<Object, Object> activeDeactiveActivity(Long id, int status,ServletRequest servletRequest) {
	
		HashMap<Object, Object> map = new HashMap<>();
		try
		{
			BattallionActivity activity = battalionActivityRepo.findById(id).get();
			if(activity!= null)
			{
				activity.setStatus(status);
				activity = battalionActivityRepo.save(activity);
				
				FileWritting.createLog((HttpServletRequest) servletRequest,activity.getId() + ",status-update," + "battallionActivityAward," + ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
				
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.STATUS_UPDATED_SUCCESSFULLY);
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
//Update Ki Apis  banano h	

	
}
