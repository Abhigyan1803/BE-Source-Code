package com.example.demo.service;

import java.util.List;

import com.example.demo.model.CadetAssignmentAnswer;

public interface CadetAssignmentAnswerService {

	public CadetAssignmentAnswer addCadetAssignmentAnswer(CadetAssignmentAnswer cadetAssignmentAnswer);

	public CadetAssignmentAnswer getById(Long id);

	public List<CadetAssignmentAnswer> getCadetAssignmentAnswerByServiceId(String serviceId, Integer status);

	public List<CadetAssignmentAnswer> getByStatus(Integer status);

	public CadetAssignmentAnswer updateCadetAssignmentAnswer(CadetAssignmentAnswer cadetAssignmentAnswer);

	public List<CadetAssignmentAnswer> getCadetAssignmentAnswerByAcdAsnId(Long acdAsnId, Integer status);

	public CadetAssignmentAnswer getByAcdAsnIdAndServiceId(Long acdAsnId, String serviceId);

}
