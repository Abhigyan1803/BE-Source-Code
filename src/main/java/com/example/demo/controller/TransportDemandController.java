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

import com.example.demo.payload.AddTransportDemad;
import com.example.demo.payload.GetDataOnlyById;
import com.example.demo.service.TransportDemandService;

@RestController
@RequestMapping("/api/transportDemandController")
@CrossOrigin
public class TransportDemandController {

	@Autowired
	TransportDemandService transportDemandService;
	
	@PostMapping("/addTransportDemand")
	public Map<Object,Object> addExercise
	(@RequestParam MultipartFile file,AddTransportDemad request , ServletRequest servletRequest)
	{
		return transportDemandService.addTransportDemand(file,request,servletRequest);
	}
	
	//@ApiOperation(value="Get all daily programes")
	
	@PostMapping("/getAllTransportDemand")
	public Map<Object,Object> getAllTranportDemand()
	{
		return transportDemandService.getAllTransportDemand();
	}
	
	@PostMapping("/getTransportById")
	public Map<Object,Object> getTranportDemandById(GetDataOnlyById request)
	{
		return transportDemandService.getDetailsByOnlyById(request);
	}
	
	@PostMapping("/activeDeActiveTransport")
	public Map<Object , Object>activeDeactiveTranportDemand(Long id,int status, ServletRequest servletRequest){
		return transportDemandService.activeDeActiveTransaport(id,status,servletRequest);
	}
	
	@PostMapping("/updateTranport")
	public Map<Object,Object> updateTransport
	(@RequestParam(required = false,value ="file") MultipartFile file,Long id,String name,String description,int status, ServletRequest servletRequest)
	{
		return transportDemandService.updateTransportDemand(file,id,name,description,status, servletRequest);
	}
	
	
}
