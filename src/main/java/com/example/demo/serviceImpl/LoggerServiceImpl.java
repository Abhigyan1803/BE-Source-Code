package com.example.demo.serviceImpl;

import java.util.logging.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.model.Admin;
import com.example.demo.model.Logging;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.LoggerRepo;
import com.example.demo.service.LoggerService;
import com.example.demo.util.ConstantVar;

@Service
public class LoggerServiceImpl implements LoggerService {

	@Autowired
	LoggerRepo loggerRepo;

	@Autowired
	AdminRepository adminRepo;
	Logger logger = Logger.getLogger(LoggerServiceImpl.class.getName());

	@Override
	public void createLogger(String username, String action, Long objId, String objName, String section,
			String message) {
		try {
			Admin adminDetails = adminRepo.findByUsername(username);

			Logging logg = new Logging();
			if (adminDetails != null) {
				logg.setUsername(adminDetails.getUsername());
				logg.setuId(adminDetails.getAdminId());
			}

			logg.setAction(action);
			logg.setObjId(objId);
			logg.setObjName(objName);
			logg.setSection(section);
			logg.setMessage(message);

			loggerRepo.save(logg);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.toString());

		}
	}

	@Override
	public void createLog(ServletRequest request, String action, Long objId, String objName, String section,
			String message) {
		String header = ((HttpServletRequest) request).getHeader(ConstantVar.HEADER_STRING);
		String username = null;
		String authToken = null;
		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		if (header != null && header.startsWith(ConstantVar.TOKEN_PREFIX)) {
			authToken = header.replace(ConstantVar.TOKEN_PREFIX, "");
			username = jwtTokenUtil.getUsernameFromToken(authToken);
		}
		createLogger(username, action, objId, objName, section, message);

	}

}
