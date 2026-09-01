package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.model.OqMarksResult;
import com.example.demo.payload.OqMarksFilterPayload;
import com.example.demo.payload.OqMarksPayload;

public interface OqMarksResultService {
	OqMarksResult createOqMarkResult(OqMarksResult oqMarksResult);

//	OqMarkResultResponse findByServiceIdAndTermIdAndEntryTypeId(String serviceId, int termId, long entryTypeId);
	OqMarksResult findByServiceIdAndTermIdAndEntryTypeId(String serviceId, int termId, long entryTypeId);

	OqMarksResult updateOqMarkResult(OqMarksResult oqMarksResult);

	List<OqMarksResult> findByServiceIdOrderBySubjectId(String serviceId);

	OqMarksPayload getCadetsByTermIdAndBattaionAndCompanyAndEntryTypeId(Long termId, String battalion, String company,
			Long entryTypeId, String serviceId, Pageable pageable);

	String updateBulkOqMarksResult(List<OqMarksFilterPayload> orMarksPayloadList);

	OqMarksPayload getCadetsBySearch(String serviceId, Long termId, Long entryTypeId, Pageable pageable);

	List<OqMarksResult> findByServiceIdAndEntryTypeId(String serviceId, Long entryTypeId);
}
