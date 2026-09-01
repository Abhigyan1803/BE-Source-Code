package com.example.demo.service;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Letter;

public interface LetterService {

	Map<Object, Object> addLetter(MultipartFile document, Letter letter, ServletRequest request);

	Map<Object, Object> getAllRecords();

	Map<Object, Object> viewDetailsById(Long id);

	Map<Object, Object> updateRecord(MultipartFile document, Letter letter, ServletRequest request);

	Map<Object, Object> activeDeactiveStatus(Long id, int status, ServletRequest request);
}
