package com.example.demo.service;

import java.util.List;

import com.example.demo.model.PCABAndCOA;

public interface AdminPCABAndCOAService {

	PCABAndCOA createPCABAndCOA(PCABAndCOA pcab);

	List<PCABAndCOA> getAllPCABAndCOAList(Integer status);

	PCABAndCOA getPCABAndCOAById(Integer id);

	PCABAndCOA updatePCABAndCOA(PCABAndCOA pcab);

}
