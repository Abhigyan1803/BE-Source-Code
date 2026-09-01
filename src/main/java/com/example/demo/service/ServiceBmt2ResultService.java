package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.model.ServiceBmt2Result;
import com.example.demo.payload.ServiceBmt2FilterPayload;
import com.example.demo.payload.ServiceBmt2Payload;

public interface ServiceBmt2ResultService {

	ServiceBmt2Result createServiceBmt2Result(ServiceBmt2Result serviceBmt2Result);

	ServiceBmt2Result updateServiceBmt2Result(ServiceBmt2Result serviceBmt2Result);

	ServiceBmt2Result findByServiceIdAndTermId(String serviceId, Long termId);

	ServiceBmt2Payload getCadetsByTermIdAndBattaionAndCompany(Long termId, String battalion, String company,
			String serviceId, Pageable pageable);

	String updateBulkServiceBmt2Result(List<ServiceBmt2FilterPayload> serviceBmt2FilterPayload);

	ServiceBmt2Payload getCadetsBySearch(Long termId, String serviceId, Pageable pageable);

}
