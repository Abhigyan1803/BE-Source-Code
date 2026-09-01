package com.example.demo.controller;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.payload.AddSopDetails;
import com.example.demo.payload.GetDataOnlyById;
import com.example.demo.service.SopsDetailsService;

@RestController
@RequestMapping("/api/SopsController")
@CrossOrigin
public class SopsDetailsController {

		@Autowired
		SopsDetailsService sopsDetailsService;
		
		@PostMapping("/addSopsDetails")
		public Map<Object,Object> addSopDetails
		(@RequestParam MultipartFile file,AddSopDetails request,ServletRequest servletRequest)
		{
			return sopsDetailsService.addSopsDetails(file,request,servletRequest);
		}
		
		//@ApiOperation(value="Get all daily programes")
		@PostMapping("/getAllSopsDetails")
		public Map<Object,Object> getAllSopsDetails()
		{
			return sopsDetailsService.getAllSopsDetails();
		}
		
		@PostMapping("/getSopsById")
		public Map<Object,Object> getSopsById(GetDataOnlyById request)
		{
			return sopsDetailsService.getDetailsByOnlyById(request);
		}
		
	
		@PostMapping("/activeDeActiveSops")
		public Map<Object , Object> activeDeactiveSopas(Long id,int status,ServletRequest servletRequest){
			return sopsDetailsService.activeDeActiveSops(id,status,servletRequest);
		}

		@PostMapping("/updateSops")
		public Map<Object,Object> updateSops
		(@RequestParam(required = false , value ="file") MultipartFile file,Long id,String name,String description,int status,ServletRequest servletRequest)
		{
			return sopsDetailsService.updateSops(file,id,name,description,status,servletRequest);
		}

		
		@PostMapping("/readRecords")
		public Map<Object,Object> readRecords
		()
		{
			return sopsDetailsService.readRecords();
		}
		
}
