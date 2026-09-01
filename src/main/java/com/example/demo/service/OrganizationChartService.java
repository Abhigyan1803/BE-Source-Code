package com.example.demo.service;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.payload.OrganizationChartPayload;

public interface OrganizationChartService {

	Map<Object, Object> addOrgPosition(String name, String rank, Long position, MultipartFile image, int status,
			String award);

	Map<Object, Object> getAllOrgPositions();

	Map<Object, Object> updateOrgPosition(Long id, String name, String rank, Long position, MultipartFile image,
			int status, String award);

	Map<Object, Object> viewDetailsById(Long id);

	Map<Object, Object> activeDeactiveStatus(Long id, int status, ServletRequest request);

	Map<Object, Object> getAllActivePositions();

	Map<Object, Object> addOrgPosition(MultipartFile image, OrganizationChartPayload payload, ServletRequest request);

	Map<Object, Object> updateOrgPosition(MultipartFile image, OrganizationChartPayload payload,
			ServletRequest request);

	Map<Object, Object> getAllTeamMembers();

	Map<Object, Object> activeDeactiveMemberStatus(Long id, int status);
}
