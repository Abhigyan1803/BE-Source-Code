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

import com.example.demo.model.NominalRole;
import com.example.demo.model.TransportDemand;
import com.example.demo.payload.AddSopDetails;
import com.example.demo.payload.AddTransportDemad;
import com.example.demo.payload.GetDataOnlyById;
import com.example.demo.payload.PaginationPayLoad;
import com.example.demo.repository.NominalRoleRepo;
import com.example.demo.repository.SopDetailsRepo;
import com.example.demo.repository.TransportDemandRepo;
import com.example.demo.service.NominalRoleService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;

@Service
public class NominalRoleServiceImpl implements NominalRoleService{


	@Autowired
	NominalRoleRepo nominalRoleRepo;
	
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;
	
	@Override
	public Map<Object, Object> addNominalRole(MultipartFile file,AddSopDetails request,ServletRequest servletRequest) {
		HashMap<Object, Object> map = new HashMap<>();
		try
		{
			NominalRole NominalRole = new NominalRole();
				if(file != null && !file.isEmpty()) {
				
				String filename = FileUploader.uploadProfileImage(file,UploadDir);
				NominalRole.setDocument(url + filename);
			}
			NominalRole.setDescription(request.getDescription());
			NominalRole.setName(request.getName());
			NominalRole.setDate(new Date());	
			NominalRole.setStatus(request.getStatus());
			
			NominalRole nominalRoleNew = nominalRoleRepo.save(NominalRole);
			if(nominalRoleNew!= null)
			{
				FileWritting.createLog((HttpServletRequest) servletRequest,nominalRoleNew.getId() + ",added," + "addNominalDetails," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.OBJECT_DETAILS,nominalRoleNew);
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
	public Map<Object, Object> getAllNominalRole() {
		HashMap<Object, Object> map = new HashMap<>();
		try
		{
			// Pageable pagedData=PageRequest.of(request.getpNumber(),request.getPageSize());
				List<NominalRole> nominalRoleList = nominalRoleRepo.findAllByOrderByIdDesc();
				if(!nominalRoleList.isEmpty())
				{
					map.put(ConstantMessage.LIST,nominalRoleList);
					//map.put(ConstantMessage.LIST_SIZE,nominalRoleList.getTotalElements());
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
			NominalRole nominalDetails=nominalRoleRepo.findById(request.getId()).get();
		
		if(nominalDetails!=null) 
		{
				map.put(ConstantMessage.OBJECT_DETAILS,nominalDetails);
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
	public Map<Object, Object> activeDeActiveNominal(Long id, int status,ServletRequest servletRequest) {
	
		HashMap<Object, Object> map = new HashMap<>();
		try
		{
			NominalRole nominalRole = nominalRoleRepo.findById(id).get();
			if(nominalRole!= null)
			{
				nominalRole.setStatus(status);
				nominalRole = nominalRoleRepo.save(nominalRole);
				
				FileWritting.createLog((HttpServletRequest) servletRequest,nominalRole.getId() + ",status-update," + "activeDeActiveNominal," + ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
				
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
	public Map<Object, Object> updateNominal(MultipartFile file,Long id,String name,String description,int status,ServletRequest servletRequest) {
	
		HashMap<Object, Object> map = new HashMap<>();
		try
		{
			NominalRole nominalRole=nominalRoleRepo.findById(id).get();			
		  
			if(nominalRole!=null) 
			{
				if(file != null && !file.isEmpty()) {
					
					String filename = FileUploader.uploadProfileImage(file,UploadDir);
					nominalRole.setDocument(url+filename);
				}
				nominalRole.setDescription(description);
				nominalRole.setName(name);
				nominalRole.setStatus(status);
	            nominalRole.setUpdatedDate(new Date());
				NominalRole nominalRoleNew=nominalRoleRepo.save(nominalRole);
				
			if(nominalRoleNew != null)
			{
				FileWritting.createLog((HttpServletRequest) servletRequest,nominalRoleNew.getId() + ",update," + "updateNominal," + ConstantMessage.RECORD_UPDATED_SUCCESSFULLY + "," + new Date());
				
				map.put(ConstantMessage.OBJECT_DETAILS,nominalRoleNew);
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
