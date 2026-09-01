package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.model.Battalion;
import com.example.demo.model.BattalionCompany;
import com.example.demo.model.BattalionHistory;
import com.example.demo.model.BattalionOrganizationChart;
import com.example.demo.model.RunbackRouteMr;
import com.example.demo.model.TrgBattalionPost;
import com.example.demo.payload.RouteRunMrFilterPayload;
import com.example.demo.payload.RouteRunMrPayload;

public interface AdminBattalionService {

	Battalion createBattalion(Battalion battalion);

	List<Battalion> getAllBattalionList();

	Battalion updateBattalion(Battalion battalion);

	List<BattalionCompany> getAllBattalionCompany();

	BattalionOrganizationChart addBattalionOrganization(BattalionOrganizationChart btOrg);

	BattalionOrganizationChart updateBattalionOrganization(BattalionOrganizationChart btOrg);

	List<BattalionOrganizationChart> getAllBattalionOrg(int battalionId, int status);

	BattalionOrganizationChart viewBattalionOrg(Long id);

	BattalionOrganizationChart updateOrganizationStatus(Long id, int status);

	BattalionHistory addBattalionHistory(BattalionHistory history);

	List<BattalionHistory> getAllHistory();

	BattalionHistory viewHistoryById(Long id);

	BattalionHistory updateHistory(BattalionHistory history);

	BattalionHistory updateHistoryStatus(Long id, int status);

	List<BattalionCompany> getBattalionSpecificCompany(int id);

	BattalionHistory activeBattalionHistoryRecord(Integer battalionId);

	List<TrgBattalionPost> getBattalionPostList();

	RunbackRouteMr createRunbackRouteMr(RunbackRouteMr runbackRouteMr);

	RunbackRouteMr getResultByServiceIdAndResultType(String serviceId, String resultType);

	List<RunbackRouteMr> getAllByStatus(Integer status);

	RunbackRouteMr updateRunbackRouteMr(RunbackRouteMr runbackRouteMr);

	RunbackRouteMr getResultByServiceIdAndTermId(String serviceId, Long termId);

	RunbackRouteMr getResultByServiceIdAndResultTypeAndTermId(String serviceId, String resultType, Long termId);

	RouteRunMrPayload getCadetsByTermIdAndBattaionAndCompany(String resultType, Long termId, String battalion,
			String company, Pageable pageable);

	RouteRunMrPayload getCadetsResultBySearch(Long termId, String resultType, String serviceId, Pageable pageable);

	String updateBulkRouteRunMrResult(List<RouteRunMrFilterPayload> routeRunMrPayloadList);

	Battalion getBattalionByShortName(String battalian);

}
