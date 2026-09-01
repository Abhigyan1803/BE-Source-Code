package com.example.demo.service;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.payload.GeneralInstructionReqPayload;
import com.example.demo.payload.UpdateGeneralInstructionPayload;

public interface AdvCellSopsService {

	Map<Object, Object> addAdvCellSops(GeneralInstructionReqPayload reqPayload, MultipartFile document,
			ServletRequest request);

	Map<Object, Object> getAllAdvCellSops();

	Map<Object, Object> updateadvCellSops(UpdateGeneralInstructionPayload update, MultipartFile document,
			ServletRequest request);

	Map<Object, Object> viewDetailsById(Long id);

	Map<Object, Object> activeDeactiveStatus(Long id, int status, ServletRequest request);

	Map<Object, Object> getSopsByStatus(int status);
}
