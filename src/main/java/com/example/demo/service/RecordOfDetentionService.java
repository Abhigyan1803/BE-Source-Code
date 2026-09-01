package com.example.demo.service;

import java.util.List;

import com.example.demo.model.RecordOfDetention;

public interface RecordOfDetentionService {

	RecordOfDetention addRecordOfDetention(RecordOfDetention recordOfDetention);

	RecordOfDetention getById(Long id);

	List<RecordOfDetention> getBystatus(Integer status);

	RecordOfDetention updateRecordOfDetention(RecordOfDetention recordOfDetention);

	RecordOfDetention getByServiceId(String serviceId);

	RecordOfDetention findByServiceIdAndTermId(String serviceId, Long termId);

}
