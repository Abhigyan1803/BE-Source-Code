//package com.example.demo.util;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.example.demo.config.JwtTokenUtil;
//import com.example.demo.model.Admin;
//import com.example.demo.model.Logging;
//import com.example.demo.repository.AdminRepository;
//import com.example.demo.repository.LoggerRepo;
//import com.example.demo.service.LoggerService;
//
//public class LoggerUtil {
//
////	@Autowired
////	JwtTokenUtil jwtTokenUtil;
//
//	@Autowired
//	LoggerService loggerService;
//
//	public void createLog(ServletRequest request, String action, Long objId, String objName, String section,
//			String message) {
//
//		String header = ((HttpServletRequest) request).getHeader(ConstantVar.HEADER_STRING);
//		String username = null;
//		String authToken = null;
//		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
//		if (header != null && header.startsWith(ConstantVar.TOKEN_PREFIX)) {
//			authToken = header.replace(ConstantVar.TOKEN_PREFIX, "");
//			username = jwtTokenUtil.getUsernameFromToken(authToken);
//		}
//		loggerService.createLogger(username, action, objId, objName, section, message);
//	}
//
//}
