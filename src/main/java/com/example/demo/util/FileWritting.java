package com.example.demo.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.example.demo.config.JwtTokenUtil;

public class FileWritting {

	// private static String LogDir = "E:/Advantal_Workspace/ImaLms/uploads/";
	// private static String LogDir = "C:/xampp/htdocs/LMSImages/";
	// server
	private static String LogDir = "C:/Program Files (x86)/Apache Software Foundation/Tomcat 9.0/Ima_logs/";

	public static void loggingHistoryFileWriter(String filePath, String message) {
		try {

			FileWriter fw = new FileWriter(filePath, true);
			BufferedWriter out = new BufferedWriter(fw);
			out.write(message);
			out.newLine();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<String> readFile(String filePath) {
		List<String> words = new ArrayList<String>();
		try {

			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = reader.readLine()) != null) {
				words.add(line);
			}
			reader.close();
			Collections.reverse(words);
			System.out.println(words);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return words;
	}

	public static void createLog(HttpServletRequest request, String message) {
		String client = getClientIpAddress(request);
		String header = ((HttpServletRequest) request).getHeader(ConstantVar.HEADER_STRING);
		String username = null;
		String authToken = null;
		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		if (header != null && header.startsWith(ConstantVar.TOKEN_PREFIX)) {
			authToken = header.replace(ConstantVar.TOKEN_PREFIX, "");
			username = jwtTokenUtil.getUsernameFromToken(authToken);
		}
		message = username + "," + client + "," + message;

		String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		String fileName = year + ".txt";
		System.out.println("File........................");
		System.out.println(fileName);
		try {

			File myObj = new File(LogDir + fileName);
			if (myObj.createNewFile()) {
				myObj.getName();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Dir........................");
		System.out.println(LogDir);
		loggingHistoryFileWriter(LogDir + fileName, message);
		// loggingHistoryFileWriter(
		// "C:/Program Files (x86)/Apache Software Foundation/Tomcat
		// 9.0/webapps/Uploads/ima/logs.txt", message);
	}

	public static void main(String[] args) {

		FileWritting.loggingHistoryFileWriter("C:/xampp/htdocs/FyrrrImages/words.txt",
				"1,admin,add,1,cadet1,cadet,cadet added 3");
		FileWritting.loggingHistoryFileWriter("C:/xampp/htdocs/FyrrrImages/words.txt",
				"1,admin,login,,,,login success 4");

		FileWritting.readFile("C:/xampp/htdocs/FyrrrImages/words.txt");

	}
	// data[0]= serviceId
	// data[1]= userName
	// data[2]=objectId
	// data[3]=objectName
	// data[4]= section
	// data[5]= message
	// data[6]=date

	public static String getClientIpAddress(HttpServletRequest request) {
		final String LOCALHOST_IPV4 = "127.0.0.1";
		final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

		String ipAddress = request.getHeader("X-Forwarded-For");

		if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}

		if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}

		if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (LOCALHOST_IPV4.equals(ipAddress) || LOCALHOST_IPV6.equals(ipAddress)) {
				try {
					InetAddress inetAddress = InetAddress.getLocalHost();
					ipAddress = inetAddress.getHostAddress();

				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		}

		if (!StringUtils.isEmpty(ipAddress) && ipAddress.length() > 15 && ipAddress.indexOf(",") > 0) {
			ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
		}
		String userAgent = request.getHeader("User-Agent");
		String response = ipAddress.concat("," + userAgent);
		return response;
	}

}
