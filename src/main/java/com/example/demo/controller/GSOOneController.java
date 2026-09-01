package com.example.demo.controller;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.GSO_OneTrgService;



@RestController
@CrossOrigin
@RequestMapping("/api/gsoContoller")
public class GSOOneController {
	
	@Autowired
	GSO_OneTrgService gsoService;
	
//	@ApiOperation(value="Add GSO_1 training module")
	@PostMapping("/addGSOOneTrg")
	public Map<Object,Object> addGSO_OneModule(@RequestParam( required = false , value ="document")MultipartFile document,@RequestParam(value="description")String description,@RequestParam(value="title")String title,@RequestParam(value="status")int status,ServletRequest servletRequest)
	{
		return gsoService.addTrgModule(document, description, title, status,servletRequest);
	}
	
//	@ApiOperation(value="Get all records")
	@GetMapping("/getAllModules")
	public Map<Object,Object> getAllModules()
	{
		return gsoService.getAllTrgModule();
	}
	
//	@ApiOperation(value="Update records of training module")
	@PostMapping("/updateModule")
	public Map<Object,Object> updateModule(@RequestParam(value="id")Long id,@RequestParam( required = false , value ="document")MultipartFile document,@RequestParam(value="description")String description,@RequestParam(value="title")String title,@RequestParam(value="status")int status,ServletRequest servletRequest)
	{
		return gsoService.updateTrgModule(id, document, description, title, status,servletRequest);
	}
	
//	@ApiOperation(value="Active deactive status of module. Pass status=0 for deactive and status=1 for active")
	@PostMapping("/activeDeactiveStatus")
	public Map<Object,Object> activeDeactiveStatus(@RequestParam(value="id")Long id,@RequestParam(value="status")int status,ServletRequest servletRequest)
	{
		return gsoService.activeDeactiveStatus(id, status,servletRequest);
	}
	
//	@ApiOperation(value="Get records by id")
	@PostMapping("/getModuleById")
	public Map<Object,Object> getTrgDetailsById(@RequestParam(value="id") Long id)
	{
		return gsoService.getTrgDetailsById(id);
	}
	
	//-------------------------------------  Schedule of central lec ---------------------------------------------
	
	@PostMapping("/addCentralLecture")
	public Map<Object,Object> addCentralLecture(@RequestParam( required = false , value ="document")MultipartFile document,@RequestParam(value="description")String description,@RequestParam(value="title")String title,@RequestParam(value="status")int status,ServletRequest servletRequest)
	{
		return gsoService.addCentralLecture(document, description, title, status,servletRequest);
	}

	
	@GetMapping("/getAllCentralLecture")
	public Map<Object,Object> getAllCentralLecture()
	{
		return gsoService.getAllCentralLecture();
	}
	
	
	@PostMapping("/updateCentralLecture")
	public Map<Object,Object> updateCentralLecture(@RequestParam(value="id")Long id,@RequestParam( required = false , value ="document")MultipartFile document,@RequestParam(value="description")String description,@RequestParam(value="title")String title,@RequestParam(value="status")int status,ServletRequest servletRequest)
	{
		return gsoService.updateCentralLecture(id, document, description, title, status,servletRequest);
	}
	
	
	@PostMapping("/activeDeactiveStatusCentralLec")
	public Map<Object,Object> activeDeactiveStatusCentralLec(@RequestParam(value="id")Long id,@RequestParam(value="status")int status,ServletRequest servletRequest)
	{
		return gsoService.activeDeactiveStatusCentralLec(id, status,servletRequest);
	}
	
	
	@PostMapping("/getCentralLectureById")
	public Map<Object,Object> getCentralLectureById(@RequestParam(value="id") Long id)
	{
		return gsoService.getCentralLectureById(id);
	}
	
}
