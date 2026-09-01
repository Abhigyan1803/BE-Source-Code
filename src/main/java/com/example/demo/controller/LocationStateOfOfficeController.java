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

import com.example.demo.model.LocationStateOfOffices;
import com.example.demo.service.LocationStateOfOfficesService;

@RestController
@CrossOrigin
@RequestMapping("/location_state")
public class LocationStateOfOfficeController {
	
	@Autowired
	LocationStateOfOfficesService locationServices;
	
	@PostMapping("/add")
	Map<Object,Object> addDetails(@RequestParam(value="doc", required=false)MultipartFile doc ,LocationStateOfOffices record,ServletRequest servletRequest)
	{
		return locationServices.addDetails(doc, record,servletRequest);
	}
	
	@PostMapping("/update")
	Map<Object,Object> updateDetails(@RequestParam(value="doc", required=false)MultipartFile doc ,LocationStateOfOffices record,ServletRequest servletRequest)
	{
		return locationServices.updateDetails(doc, record,servletRequest);
	}
	
	@PostMapping("/viewById")
	Map<Object,Object> viewDetails(Long id)
	{
		return locationServices.viewById(id);
	}
	
    @PostMapping("/getAll")
    Map<Object,Object> getAll()
    {
    	return locationServices.getAllDetails();
    }
    @PostMapping("/activeDeactiveStatus")
    Map<Object,Object> activeDeactiveStatus(Long id,int status,ServletRequest servletRequest)
    {
    	return locationServices.activeDeactiveStatus(id, status,servletRequest);
    }
}
