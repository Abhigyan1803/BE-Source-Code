package com.example.demo.service;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface IMAActivitiesService {

	Map<Object, Object> addActivity(MultipartFile file, int status, ServletRequest request);

	Map<Object, Object> getActivitiesByStatus(int status);

	Map<Object, Object> activeDeactiveActivity(Long id, int status, ServletRequest request);

	Map<Object, Object> getAllActivities();

}
