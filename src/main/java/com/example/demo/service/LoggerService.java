package com.example.demo.service;

import javax.servlet.ServletRequest;

public interface LoggerService {

	public void createLog(ServletRequest request, String action, Long objId, String objName, String section,
			String message);

	void createLogger(String username, String action, Long objId, String objName, String section, String message);
}
