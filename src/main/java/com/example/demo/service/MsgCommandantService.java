package com.example.demo.service;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.payload.MsgCommandantReqPayload;

public interface MsgCommandantService {

	Map<Object, Object> addMessageCommandant(MsgCommandantReqPayload msgPayload, MultipartFile file,
			ServletRequest request);

	Map<Object, Object> getAllMessages();

	Map<Object, Object> getMessagesByStatus(int status);

	Map<Object, Object> ActiveDeactiveMessgae(Long id, int status, ServletRequest request);

	Map<Object, Object> updateMessageCommandant(MsgCommandantReqPayload msgCommandant, MultipartFile image,
			ServletRequest request);

	Map<Object, Object> viewMessageById(Long id);
}
