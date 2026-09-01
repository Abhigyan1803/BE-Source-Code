package com.example.demo.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.payload.MsgCommandantReqPayload;
import com.example.demo.service.MsgCommandantService;

//import io.swagger.annotations.ApiOperation;

//@RestController
//@RequestMapping("/api/messageCommandantController")
//@CrossOrigin
public class MessageCommandantController {
//	
//	@Autowired
//	MsgCommandantService msgService;
//	
//	
////	@ApiOperation(value = "Add message ")
//	@PostMapping("/addMessageNew")
//	public Map<Object, Object> addMessagereturn(@RequestParam( required = false , value ="image") MultipartFile image,MsgCommandantReqPayload msgCommandant) {
//		//HashMap<Object, Object> map = new HashMap<>();
//		
//			return msgService.addMessageCommandant(msgCommandant, image);
//		
//	}
//
//	@PostMapping("/getMessage")
//	public String addMessage() {
//		//HashMap<Object, Object> map = new HashMap<>();
//		
//			return "Hello";
//		
//	}

}
