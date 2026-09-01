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

import com.example.demo.model.ParadeStateOfGCS;
import com.example.demo.service.ParadeStateofGCSService;

@RestController
@CrossOrigin
@RequestMapping("/parade_state")
public class ParadeStateOfGCSController {

	@Autowired
	ParadeStateofGCSService paradeService;
	
	
	@PostMapping("/add")
	Map<Object,Object> addDetails(@RequestParam(value="doc" , required=false)MultipartFile doc, ParadeStateOfGCS parade,ServletRequest servletRequest)
	{
		return paradeService.addDetails(doc, parade,servletRequest);
	}
	
	@PostMapping("/update")
	Map<Object,Object> updateDetails(@RequestParam(value="doc" , required=false)MultipartFile doc, ParadeStateOfGCS parade,ServletRequest servletRequest)
	{
		return paradeService.updateDetails(doc, parade,servletRequest);
	}
	
	@PostMapping("/viewById")
	Map<Object,Object> viewDetails(Long id)
	{
		return paradeService.viewById(id);
	}
	
	@PostMapping("/activeDeactiveStatus")
	Map<Object,Object> activeDeactiveStatus(Long id,int status,ServletRequest servletRequest)
	{
		return paradeService.activeDeactiveStatus(id, status,servletRequest);
	}
	
	@PostMapping("/getAll")
	Map<Object,Object> getAll()
	{
		return paradeService.getAllDetails();
	}
}
