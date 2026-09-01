package com.example.demo.service;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.payload.GeneralInstructionReqPayload;
import com.example.demo.payload.UpdateGeneralInstructionPayload;

public interface GeneralInstuctionService {

	Map<Object, Object> addGeneralInsturction(GeneralInstructionReqPayload reqPayload, MultipartFile document,
			ServletRequest request);

	Map<Object, Object> getAllInstruction();

	Map<Object, Object> updateGeneralInstruction(UpdateGeneralInstructionPayload update, MultipartFile document,
			ServletRequest request);

	Map<Object, Object> viewDetailsById(Long id);

	Map<Object, Object> activeDeactiveStatus(Long id, int status, ServletRequest request);

	Map<Object, Object> getInstructionsByStatus(int status);

}
