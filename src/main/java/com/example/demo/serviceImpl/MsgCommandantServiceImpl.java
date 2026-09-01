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

import com.example.demo.model.MessageCommandant;
import com.example.demo.payload.MsgCommandantReqPayload;
import com.example.demo.repository.MessageCommandantRepo;
import com.example.demo.service.MsgCommandantService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;

@Service
public class MsgCommandantServiceImpl implements MsgCommandantService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	MessageCommandantRepo msgRepo;

	@Override
	public Map<Object, Object> addMessageCommandant(MsgCommandantReqPayload msgPayload, MultipartFile img,
			ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		MessageCommandant msgCommandant = new MessageCommandant();
		try {
			if (img != null && !img.isEmpty()) {

				String filename = FileUploader.uploadProfileImage(img, UploadDir);
				msgCommandant.setImage(url + filename);
			}
			msgCommandant.setName(msgPayload.getName());
			msgCommandant.setAward(msgPayload.getAward());
			msgCommandant.setDesignation(msgPayload.getDesignation());
			msgCommandant.setMessage(msgPayload.getMessage());
			msgCommandant.setOrganization(msgPayload.getOrganization());
			msgCommandant.setStatus(msgPayload.getStatus());
			msgCommandant.setDate(msgPayload.getDate());

			msgCommandant = msgRepo.save(msgCommandant);

			if (msgCommandant != null) {
				FileWritting.createLog((HttpServletRequest) request, msgCommandant.getId() + ",added,"
						+ "add Message Commandant," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.LIST, msgCommandant);
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
	public Map<Object, Object> getAllMessages() {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			List<MessageCommandant> messageList = msgRepo.findAllByOrderByIdDesc();
			/*
			 * List<MsgCommandantReqPayload> msgList = new ArrayList<>();
			 * for(MessageCommandant msg : messageList) { MsgCommandantReqPayload msgPayload
			 * = new MsgCommandantReqPayload();
			 *
			 * msgPayload.setId(msg.getId()); msgPayload.setName(msg.getName());
			 * msgPayload.setAward(msg.getAward());
			 * msgPayload.setDesignation(msg.getDesignation());
			 * msgPayload.setMessage(msg.getMessage());
			 * msgPayload.setOrganization(msg.getOrganization());
			 * msgPayload.setStatus(msg.getStatus());
			 * msgPayload.setCreated_at(msg.getCreated_at());
			 *
			 * msgList.add(msgPayload); }
			 */
			if (messageList.size() != 0) {
				map.put(ConstantMessage.LIST, messageList);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);

		}
		return map;
	}

	@Override
	public Map<Object, Object> getMessagesByStatus(int status) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			Integer[] deletedStatus = { 2 };
			List<MessageCommandant> messageList = msgRepo.findByStatusAndStatusNotInOrderByIdDesc(status,
					deletedStatus);

			if (messageList.size() != 0) {
				map.put(ConstantMessage.LIST, messageList.get(0));
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);

			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);

		}
		return map;
	}

	@Override
	public Map<Object, Object> ActiveDeactiveMessgae(Long id, int status, ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			MessageCommandant msg = msgRepo.findById(id).get();
			if (msg != null) {
				msg.setStatus(status);
				msg = msgRepo.save(msg);

				FileWritting.createLog((HttpServletRequest) request,
						msg.getId() + ",updated," + "status updated Message Commandant,"
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
	public Map<Object, Object> updateMessageCommandant(MsgCommandantReqPayload msgPayload, MultipartFile img,
			ServletRequest request) {

		HashMap<Object, Object> map = new HashMap<>();
		MessageCommandant msgCommandant = new MessageCommandant();
		try {
			MessageCommandant details = msgRepo.findById(msgPayload.getId()).get();
			if (details != null) {

				if (img != null && !img.isEmpty()) {

					String filename = FileUploader.uploadProfileImage(img, UploadDir);
					details.setImage(url + filename);
				}
				details.setName(msgPayload.getName());
				details.setAward(msgPayload.getAward());
				details.setDesignation(msgPayload.getDesignation());
				details.setMessage(msgPayload.getMessage());
				details.setOrganization(msgPayload.getOrganization());
				details.setStatus(msgPayload.getStatus());
				details.setUpddatedOn(new Date());
				details.setDate(msgPayload.getDate());

				msgCommandant = msgRepo.save(details);

				if (msgCommandant != null) {
					FileWritting.createLog((HttpServletRequest) request,
							msgCommandant.getId() + ",updated," + "updated Message Commandant,"
									+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
					map.put(ConstantMessage.LIST, msgCommandant);
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
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}

		return map;
	}

	@Override
	public Map<Object, Object> viewMessageById(Long id) {
		HashMap<Object, Object> map = new HashMap<>();
		// MessageCommandant msgCommandant = new MessageCommandant();
		try {
			MessageCommandant details = msgRepo.findById(id).get();
			if (details != null) {
				map.put(ConstantMessage.LIST, details);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_ADDED_SUCCESSFULLY);
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
}
