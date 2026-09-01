package com.example.demo.service;

import com.example.demo.model.PtMotivationAwards;

public interface PtMotivationAwardsService {

	PtMotivationAwards addPtMotivationAwards(PtMotivationAwards ptMotivationAwards);

	PtMotivationAwards findByServiceIdAndTermId(String serviceId, Long termId);

	PtMotivationAwards updatePtMotivationAwards(PtMotivationAwards ptMotivationAwards);

}
