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

import com.example.demo.payload.MsgCommandantReqPayload;
import com.example.demo.service.MsgCommandantService;

@RestController
@RequestMapping("/api/messageCommandantController")
@CrossOrigin
public class AdminMessageCommandantController {

	@Autowired
	MsgCommandantService msgService;

//	@ApiOperation(value = "Add message ")
	@PostMapping("/addMessage")
	public Map<Object, Object> addMessage(@RequestParam(required = false, value = "image") MultipartFile image,
			MsgCommandantReqPayload msgCommandant, ServletRequest request) {

		return msgService.addMessageCommandant(msgCommandant, image, request);
	}

//	@ApiOperation(value="Get all messages")
	@GetMapping("/getAllMessages")
	public Map<Object, Object> getAllMessages() {

		return msgService.getAllMessages();
	}

//	@ApiOperation(value="Get all messages")
	@PostMapping("/getLatestMsgByStatus")
	public Map<Object, Object> getAllMessages(int status) {
		return msgService.getMessagesByStatus(status);
	}

//	@ApiOperation(value="active Deactive")
	@PostMapping("/activeDeActiemsg")
	public Map<Object, Object> activeDeactive(Long id, int status, ServletRequest request) {
		return msgService.ActiveDeactiveMessgae(id, status, request);
	}

//	@ApiOperation(value="update profile")
	@PostMapping("/updateMessage")
	public Map<Object, Object> updateMessage(@RequestParam(required = false, value = "image") MultipartFile image,
			MsgCommandantReqPayload msgCommandant, ServletRequest request) {
		return msgService.updateMessageCommandant(msgCommandant, image, request);
	}

	@PostMapping("/viewMessageById")
	public Map<Object, Object> viewById(Long id) {
		return msgService.viewMessageById(id);
	}
}
