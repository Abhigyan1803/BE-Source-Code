package com.example.demo.service;

import com.example.demo.model.WtMotivationBadge;

public interface WtMotivationBadgeService {

	WtMotivationBadge addMotivationBadge(WtMotivationBadge wtMotivationBadge);

	WtMotivationBadge getByServiceIdAndTermId(String serviceId, Long termId);

	WtMotivationBadge getByServiceId(String serviceId);

	WtMotivationBadge updateMotivationBadge(WtMotivationBadge wtMotivationBadge);

}
