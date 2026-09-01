package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.PodCastDetails;
import com.example.demo.myexception.MyException;
import com.example.demo.service.PodCastService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/podcastController")
public class PodCastController {

	@Autowired
	PodCastService podCastService;
	
	
	@PostMapping(value = "/addPodCast")
	public ResponseEntity<?> addGreyBook(@RequestParam MultipartFile file,PodCastDetails podCastDetails,ServletRequest request) throws MyException {
		PodCastDetails response=podCastService.addPodcast(file, podCastDetails);
		FileWritting.createLog((HttpServletRequest)request ,response.getId()+ ",add,"+"addPodCast,"+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY+","+ new Date());
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
	}

	@GetMapping(value = "/getAllPodCastList")
	public ResponseEntity<?> getAllPodCastList() {
		List<PodCastDetails> list=podCastService.getAllPodCast();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/activeDeActivePodCast")
	public ResponseEntity<?> activeDeActivePodCast(@RequestParam Long id,int status,ServletRequest request) {
		 PodCastDetails response=podCastService.activeDeactivePodCast(id, status);
		 FileWritting.createLog((HttpServletRequest)request ,response.getId()+ ",status,"+"activeDeActivePodCast,"+ ConstantMessage.OK_MESSAGE+","+ new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK,response),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/updatePodCast")
	public ResponseEntity<?> updatePodCast(@RequestParam(required = false , value ="file") MultipartFile file,PodCastDetails request,ServletRequest servletReq) throws MyException {
		   PodCastDetails response=podCastService.updatePodcastRecord(file, request); 
		   FileWritting.createLog((HttpServletRequest)servletReq ,response.getId()+ ",update,"+"updatePodCast,"+ ConstantMessage.RECORD_UPDATED_SUCCESSFULLY+","+ new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getPodCastDetailsById")
	public ResponseEntity<?> getPodCastDetailsById(@RequestParam Long id) {
	PodCastDetails response=podCastService.getPodCastById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK,response),
				HttpStatus.OK);
	}

}
