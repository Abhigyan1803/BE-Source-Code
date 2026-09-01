package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Announcements;

public interface AnnouncementService {

	Announcements addAnnouncement(Announcements announcement);

	List<Announcements> getAllAnnouncementList(Integer status);

	Announcements getAnnouncementById(Long id);

	Announcements updateAnnouncement(Announcements announcement);

}
