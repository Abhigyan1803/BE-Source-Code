package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AcdClubSops;
import com.example.demo.model.AcdCounsellor;
import com.example.demo.model.GCBoard;
import com.example.demo.model.GCBoard_Pcht_Ol_Achievements;

public interface GCBoardService {

	GCBoard addGCBoard(GCBoard details, MultipartFile file);

	GCBoard updateGCBoard(GCBoard details, MultipartFile file);

	GCBoard viewById(Long id);

	List<GCBoard> getList(int status);

	GCBoard changeStatus(Long id, int status);

	GCBoard_Pcht_Ol_Achievements addDetailsGCBoard_Pcht_Ol_Achievements(GCBoard_Pcht_Ol_Achievements details,
			MultipartFile file);

	GCBoard_Pcht_Ol_Achievements viewGCBoard_Pcht_Ol_AchievementsById(Long id);

	List<GCBoard_Pcht_Ol_Achievements> getGCBoard_Pcht_Ol_AchievementsList(String type, String subType, int status);

	GCBoard_Pcht_Ol_Achievements updateGCBoard_Pcht_Ol_AchievementsDetails(GCBoard_Pcht_Ol_Achievements details,
			MultipartFile file);

	GCBoard_Pcht_Ol_Achievements changeGCBoard_Pcht_Ol_AchievementsDetailsStatus(Long id, int status);

	AcdClubSops addAcdClubSops(AcdClubSops acdClubSops, MultipartFile docfile);

	AcdClubSops updateAcdClubSops(AcdClubSops acdClubSops, MultipartFile docfile);

	AcdClubSops getAcdClubSopsById(Long id);

	List<AcdClubSops> getAcdClubSopsList(String type, String subType, String subSubType, Integer status);

	AcdClubSops changeAcdClubSopsStatus(Long id, Integer status);

	AcdCounsellor addAcdCounsellor(AcdCounsellor acdCounsellor);

	AcdCounsellor updateAcdCounsellor(AcdCounsellor acdCounsellor);

	AcdCounsellor getAcdCounsellorById(Long id);

	List<AcdCounsellor> getAcdCounsellorList(Long battalionId, Long companyId, Integer status);

}
