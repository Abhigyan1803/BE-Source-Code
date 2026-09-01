package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.CommandantDailyProgramme;
import com.example.demo.model.Exercises;
import com.example.demo.model.SopsDetails;
import com.example.demo.model.TransportDemand;
import com.example.demo.payload.AddTransportDemad;
import com.example.demo.payload.GetDataOnlyById;
import com.example.demo.payload.PaginationPayLoad;
import com.example.demo.repository.TransportDemandRepo;
import com.example.demo.service.TransportDemandService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.DateUtil;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;

@Service
public class TransportDemandServiceImpl implements TransportDemandService{

	@Autowired
	TransportDemandRepo transportDemandRepo;
	
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;
	
	@Override
	public Map<Object, Object> addTransportDemand(MultipartFile file,AddTransportDemad request,ServletRequest servletRequest) {
		HashMap<Object, Object> map = new HashMap<>();
		try
		{
			TransportDemand transportDemand = new TransportDemand();
			if(file != null && !file.isEmpty()) {
				
				String filename = FileUploader.uploadProfileImage(file,UploadDir);
				transportDemand.setDocument(url + filename);
			}
			
			transportDemand.setDescription(request.getDescription());
			transportDemand.setName(request.getName());
		    transportDemand.setStatus(request.getStatus());
		    TransportDemand transportDemandNew = transportDemandRepo.save(transportDemand);
			if(transportDemandNew!= null)
			{
				FileWritting.createLog((HttpServletRequest) servletRequest,transportDemandNew.getId() + ",added," + "addTransportDemand," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.OBJECT_DETAILS,transportDemandNew);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_ADDED_SUCCESSFULLY);
				return map;
			}
		}
		catch(Exception ex)
		{
	       ex.printStackTrace();		
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE,ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}
	
	@Override
	public Map<Object, Object> getAllTransportDemand() {
		HashMap<Object, Object> map = new HashMap<>();
		try
		{
			 //Pageable pagedData=PageRequest.of(request.getpNumber(),request.getPageSize());
				List<TransportDemand> transportDemandList = transportDemandRepo.findAllByOrderByIdDesc();
				if(transportDemandList.size()!=0)
				{
					map.put(ConstantMessage.LIST,transportDemandList);
					//map.put(ConstantMessage.LIST_SIZE,transportDemandList.getTotalElements());
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
	public Map<Object, Object> getDetailsByOnlyById(GetDataOnlyById request) {
		HashMap<Object, Object> map = new HashMap<>();
		try
		{
			TransportDemand transportDemand=transportDemandRepo.findById(request.getId()).get();
		
		if(transportDemand!=null) 
		{
				map.put(ConstantMessage.OBJECT_DETAILS,transportDemand);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
		}else 
		{
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE,ConstantMessage.INVALID_ID);
		}
		}
		catch(Exception ex)
		{
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE,ConstantMessage.TECHNICAL_ISSUE);
		}
	return map;
	}

	@Override
	public Map<Object, Object> activeDeActiveTransaport(Long id, int status,ServletRequest servletRequest) {
	
		HashMap<Object, Object> map = new HashMap<>();
		try
		{
			TransportDemand transportDemand = transportDemandRepo.findById(id).get();
			if(transportDemand!= null)
			{
				transportDemand.setStatus(status);
				transportDemand = transportDemandRepo.save(transportDemand);
				
				FileWritting.createLog((HttpServletRequest) servletRequest,transportDemand.getId() + ",status-update," + "activeDeActiveTransport," + ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
				
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


	@Override
	public Map<Object, Object> updateTransportDemand(MultipartFile file,Long id,String name,String description,int status,ServletRequest servletRequest) {
	
		HashMap<Object, Object> map = new HashMap<>();
		try
		{
		    TransportDemand transportDemand=transportDemandRepo.findById(id).get();			
	
			if(transportDemand!=null) 
			{
		
				if(file != null && !file.isEmpty()) {
					
					String filename = FileUploader.uploadProfileImage(file,UploadDir);
					transportDemand.setDocument(url+filename);
				}
				
				transportDemand.setDescription(description);
				transportDemand.setName(name);
				transportDemand.setStatus(status);
				transportDemand.setUpdatedOn(new Date());	
				TransportDemand transportDemandNew=transportDemandRepo.save(transportDemand);
				
				if(transportDemandNew != null)
				{
					FileWritting.createLog((HttpServletRequest) servletRequest,transportDemandNew.getId() + ",update," + "updateTranport," + ConstantMessage.RECORD_UPDATED_SUCCESSFULLY + "," + new Date());
					map.put(ConstantMessage.OBJECT_DETAILS,transportDemandNew);
					map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
					map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_UPDATED_SUCCESSFULLY);
					return map;
				}
				else
				{
					map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
					map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
				}
			}else 
			{
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.INVALID_ID);
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return map;
	}


}
