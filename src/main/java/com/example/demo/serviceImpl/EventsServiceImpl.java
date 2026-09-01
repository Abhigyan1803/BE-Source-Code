package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.UpcomingEvents;
import com.example.demo.payload.UpcomingEventPayload;
import com.example.demo.payload.UpdateUpcomingEventsPayLoad;
import com.example.demo.repository.EventsRepository;
import com.example.demo.service.EventsService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;

@Service
public class EventsServiceImpl implements EventsService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	EventsRepository eventsRepo;

	@Override
	public Map<Object, Object> addUpcomingEvents(UpcomingEventPayload eventPayload, MultipartFile img,
			ServletRequest servletRequest) {
		HashMap<Object, Object> map = new HashMap<>();
		UpcomingEvents events = new UpcomingEvents();
		try {
			if (img != null && !img.isEmpty()) {

				String filename = FileUploader.uploadProfileImage(img, UploadDir);
				events.setImage(url + filename);
			}
			events.setDescription(eventPayload.getDescription());
			events.setTitle(eventPayload.getTitle());
			events.setEventDate(eventPayload.getEventDate());
			events.setStatus(eventPayload.getStatus());
			events.setIsGcEvent(eventPayload.getIsGcEvent());

			// events.setCreatedAt(DateUtil.convertTimeStampToDate(new Date().getTime()));

			UpcomingEvents eventsNew = eventsRepo.save(events);
			if (eventsNew != null) {
				FileWritting.createLog((HttpServletRequest) servletRequest, eventsNew.getId() + ",added,"
						+ "add Upcoming Events," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.LIST, events);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_ADDED_SUCCESSFULLY);
				return map;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

	@Override
	public Map<Object, Object> getAllUpcomingEvents(Boolean isGcEvent) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			List<UpcomingEvents> eventsList = null;
			if (isGcEvent == true) {
				eventsList = eventsRepo.findAllByIsGcEventOrderByIdDesc(isGcEvent);  
			} else {
				eventsList = eventsRepo.findAllByOrderByIdDesc();
			}

			if (eventsList.size() != 0) {
				map.put(ConstantMessage.LIST, eventsList);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);

		}
		return map;
	}

	@Override
	public Map<Object, Object> ActiveDeactiveEvents(Long id, int status, ServletRequest servletRequest) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			UpcomingEvents event = eventsRepo.findById(id).get();
			if (event != null) {
				event.setStatus(status);
				event = eventsRepo.save(event);
				FileWritting.createLog((HttpServletRequest) servletRequest,
						event.getId() + ",updated," + "status updated Upcoming Events,"
								+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.STATUS_UPDATED_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);

		}
		return map;
	}

	@Override
	public Map<Object, Object> getEventsByStatus(int status) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			Integer[] deletedStatus = { 2 };
			List<UpcomingEvents> eventsList = eventsRepo.findByStatusAndStatusNotIn(status, deletedStatus);
			if (eventsList != null) {
				map.put(ConstantMessage.LIST, eventsList);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);

		}
		return map;
	}

	@Override
	public Map<Object, Object> updateUpcomingEvents(UpdateUpcomingEventsPayLoad request, MultipartFile img,
			ServletRequest servletRequest) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			UpcomingEvents details = eventsRepo.findById(request.getId()).get();

			if (details != null) {
				if (img != null && !img.isEmpty()) {

					String filename = FileUploader.uploadProfileImage(img, UploadDir);
					details.setImage(url + filename);
				}
				details.setDescription(request.getDescription());
				details.setTitle(request.getTitle());
				details.setEventDate(request.getEventDate());
				details.setStatus(request.getStatus());
				details.setIsGcEvent(request.getIsGcEvent());
				UpcomingEvents eventsNew = eventsRepo.save(details);
				if (eventsNew != null) {
					FileWritting.createLog((HttpServletRequest) servletRequest,
							eventsNew.getId() + ",updated," + "updated Upcoming Events,"
									+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());

					map.put(ConstantMessage.LIST, eventsNew);
					map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
					map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_ADDED_SUCCESSFULLY);
					return map;
				} else {
					map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
					map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
				}

			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.INVALID_ID);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<Object, Object> UpcomingEvents(Boolean isGcEvent) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			List<UpcomingEvents> eventsList = null;
			if (isGcEvent == true) {
				// eventsList = eventsRepo.findAllByIsGcEventOrderByIdDesc(isGcEvent);
				eventsList = eventsRepo.findAllByIsGcEventOrderByIdDesc(new Date().getTime(), isGcEvent);
			} else {
				eventsList = eventsRepo.findUpcomingEventsByIsGcEvent(new Date().getTime());
				// eventsList = eventsRepo.findAllByOrderByIdDesc();
			}

			// List<UpcomingEvents> eventsList =
			// eventsRepo.findUpcomingEventsByIsGcEvent(new Date().getTime());
			if (eventsList != null && eventsList.size() != 0) {
				map.put(ConstantMessage.LIST, eventsList);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);

		}
		return map;
	}

	@Override
	public Map<Object, Object> viewEventsById(Long id) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			UpcomingEvents event = eventsRepo.findById(id).get();
			if (event != null) {
				map.put(ConstantMessage.LIST, event);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.INVALID_ID);
				return map;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);

		}
		return map;
	}

	@Override
	public Map<Object, Object> getEventsByDate(Long startDate, Long endDate) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			List<UpcomingEvents> eventsList = eventsRepo.findProgramBetweenDates(startDate, endDate);
			if (eventsList != null && eventsList.size() != 0) {
				map.put(ConstantMessage.LIST, eventsList);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);

		}
		return map;
	}

}
