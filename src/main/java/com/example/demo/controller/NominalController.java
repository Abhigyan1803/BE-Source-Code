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
import com.example.demo.service.NominalRoleService;

@RestController
@RequestMapping("/api/nominalController")
@CrossOrigin
public class NominalController {

	
	@Autowired
	NominalRoleService nominalRoleService;
	
	@PostMapping("/addNominalDetails")
	public Map<Object,Object> addNominalDetails
	(@RequestParam MultipartFile file,AddSopDetails request,ServletRequest servletRequest)
	{
		return nominalRoleService.addNominalRole(file,request,servletRequest);
	}
	
	//@ApiOperation(value="Get all daily programes")

	@PostMapping("/getAllNominalDetails")
	public Map<Object,Object> getAllNominalDetails()
	{
		return nominalRoleService.getAllNominalRole();
	}
	
	@PostMapping("/getNominalById")
	public Map<Object,Object> getNominalById(GetDataOnlyById request)
	{
		return nominalRoleService.getDetailsByOnlyById(request);
	}
	
	@PostMapping("/activeDeActiveNominal")
	public Map<Object , Object> activeDeactiveNominal(Long id,int status,ServletRequest servletRequest){
		return nominalRoleService.activeDeActiveNominal(id, status,servletRequest);
	}
	
	@PostMapping("/updateNominal")
	public Map<Object,Object> updateNominal(@RequestParam(required = false,value ="file") MultipartFile file,Long id,String name,String description,int status,ServletRequest servletRequest)
	{
		return nominalRoleService.updateNominal(file,id,name,description,status,servletRequest);
	}
	
}
