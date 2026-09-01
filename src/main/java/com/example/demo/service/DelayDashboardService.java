package com.example.demo.service;

import java.util.List;

import com.example.demo.model.DelayDashboard;
import com.example.demo.payload.DelayDashboardPayLoad;

public interface DelayDashboardService {

	DelayDashboard addDelayDashboard(DelayDashboard delayDashboard);

	DelayDashboard updateDelayDashboard(DelayDashboard delayDashboard);

	List<DelayDashboardPayLoad> getDelayDashboardStaff(Long moduleId, Long termId);

}
