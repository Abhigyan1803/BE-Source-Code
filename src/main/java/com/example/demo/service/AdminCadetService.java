package com.example.demo.service;

import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Cadet;
import com.example.demo.payload.AdminCadetPayload;
import com.example.demo.payload.CadetPayLoad;
import com.example.demo.payload.EdCadetPayload;

public interface AdminCadetService {

	Cadet createCadetDetail(Cadet cadet, List<MultipartFile> file, String url, String uploadDir);

	// Page<Cadet> getAllCadetList(Pageable paginationData);

	List<Cadet> getAllCadetList(String status);

	// List<Cadet> getAllCadetList(Integer status);

	Cadet getCadetById(Long id);

	Cadet updateCadetDetail(Cadet cadet, List<MultipartFile> file, String url, String uploadDir);

	Cadet checkServiceId(String serviceId);

	Cadet getDataByUsernameAndBattalian(String username, Integer battalionId, ServletRequest request);

	Cadet getCadetByServiceId(String servceId);

	Cadet getCadetByServiceIdAndTermId(String serviceId, Long termId);

	Cadet updateCadetByServiceId(CadetPayLoad cadetPayload);

	EdCadetPayload getCadetsByTermIdAndBattaionAndCompany(Long termId, String battalion, String company,
			Pageable pageable);

	EdCadetPayload getCadetsBySearch(String serviceId, Pageable pageable);

	String updateCadetTermById(List<Cadet> cadetList);

	EdCadetPayload getCadetsByTermIdAndBattaionAndCompanyWithoutPagination(Long termId, String battalion,
			String company);

	EdCadetPayload getCadetsBySearchWithoutPagination(String serviceId);

	AdminCadetPayload getAllCadetListWithFilterAndPagination(String status, Long termId, String battalion,
			String company, Pageable pageable);

	AdminCadetPayload getAdminCadetsBySearch(String status, String serviceId, Pageable pageable);
	
	//////////////////////////////////////////////////////////////////////////////////////////

	Cadet createCadetDetailNew(Cadet cadet, String url, String uploadDir);

}
