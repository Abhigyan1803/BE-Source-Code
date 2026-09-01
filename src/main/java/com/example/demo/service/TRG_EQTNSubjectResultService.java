package com.example.demo.service;

import com.example.demo.model.TRG_EQTNSubjectResult;

public interface TRG_EQTNSubjectResultService {

	TRG_EQTNSubjectResult updateSubResult(TRG_EQTNSubjectResult tRG_EQTNSubjectResult);

	TRG_EQTNSubjectResult getSubResultById(Long id);

	// void createEQTNSubResult(TRG_EQTNSubjectResult eQTNSubRslt);

	// TRG_EQTNSubjectResult createTRG_EQTNResult(TRG_EQTNResult tRG_EQTNResult);

	TRG_EQTNSubjectResult createSubResult(TRG_EQTNSubjectResult tRG_EQTNSubjectResult);

}
