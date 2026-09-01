package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.example.demo.model.Announcements;
import com.example.demo.myexception.MyException;
import com.example.demo.service.AnnouncementService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/announcement")
public class AnnouncementsController {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AnnouncementService announcementService;

	@PostMapping("/add-announcement")
	private ResponseEntity<?> addAnnouncement(Announcements announcement,
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date validTill,
			@RequestParam(value = "announcementDoc", required = false) MultipartFile[] file, ServletRequest request)
			throws MyException {
		if (file != null) {
			String uploaded_doc = StringUtils.EMPTY;
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				announcement.setAnnouncementDocument(url + uploaded_doc);
			}
		}
		Announcements response = announcementService.addAnnouncement(announcement);

		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "announcement," + ConstantMessage.ANNOUNCEMENT_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ANNOUNCEMENT_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-announcement-list")
	public ResponseEntity<?> getAnnouncementList(@RequestParam Integer status) {
		List<Announcements> list = announcementService.getAllAnnouncementList(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-announcement")
	public ResponseEntity<?> getAnnouncementByID(@RequestParam Long id) {
		Announcements list = announcementService.getAnnouncementById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-announcement")
	public ResponseEntity<?> updateAnnouncement(Announcements announcement,
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date validTill,
			@RequestParam(value = "announcementDoc", required = false) MultipartFile[] file, ServletRequest request)
			throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		if (file != null) {
			long length = file.length;
			System.err.println("file length " + length);
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				announcement.setAnnouncementDocument(url + uploaded_doc);
			}
		}
		Announcements response = announcementService.updateAnnouncement(announcement);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "academy-parade-state,"
				+ ConstantMessage.ANNOUNCEMENT_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ANNOUNCEMENT_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

}
