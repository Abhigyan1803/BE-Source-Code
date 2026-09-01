package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Announcements;
import com.example.demo.repository.AnnouncementRepo;
import com.example.demo.service.AnnouncementService;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

	@Autowired
	AnnouncementRepo announcementRepo;

	@Override
	public Announcements addAnnouncement(Announcements announcement) {
		return announcementRepo.save(announcement);
	}

	@Override
	public List<Announcements> getAllAnnouncementList(Integer status) {
		Integer[] deletedStatus = { 2 };
		if (status < 2) {
			List<Announcements> list = announcementRepo.findByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
			return list;
		} else {
			List<Announcements> list = announcementRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
			return list;
		}
	}

	@Override
	public Announcements getAnnouncementById(Long id) {
		Optional<Announcements> list = announcementRepo.findById(id);
		return list.get();
	}

	@Override
	public Announcements updateAnnouncement(Announcements announcement) {
		Announcements anncmnt = null;
		Optional<Announcements> a = announcementRepo.findById(announcement.getId());
		if (a.isPresent()) {

			anncmnt = a.get();

			if (StringUtils.isNotBlank(announcement.getAnnouncementDocument())) {
				anncmnt.setAnnouncementDocument(announcement.getAnnouncementDocument());
			}

			if (announcement.getAnnouncementDescp() != null) {

				anncmnt.setAnnouncementDescp(announcement.getAnnouncementDescp());
			}

			if (announcement.getValidTill() != null) {

				anncmnt.setValidTill(announcement.getValidTill());
			}

			if (announcement.getStatus() != null) {

				anncmnt.setStatus(announcement.getStatus());
			}

			anncmnt.setUpdatedAt(new Date());

		}
		Announcements list = announcementRepo.save(anncmnt);
		return list;
	}

}
