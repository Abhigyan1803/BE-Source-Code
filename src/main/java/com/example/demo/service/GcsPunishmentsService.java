package com.example.demo.service;

import java.util.List;

import com.example.demo.model.GcsPunishments;

public interface GcsPunishmentsService {

	GcsPunishments addGcspunshiments(GcsPunishments gcsPunishments);

	GcsPunishments updateGcsPunishments(GcsPunishments gcsPunishments);

	List<GcsPunishments> getGcsPunishmentsList(String serviceId, Long termId, Integer status);

}
