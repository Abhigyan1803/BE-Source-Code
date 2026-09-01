package com.example.demo.service;

import java.util.List;

import com.example.demo.model.GSStatsMatrix;

public interface AdminGSStatsMatrixService {

	GSStatsMatrix createGsMatrix(GSStatsMatrix matrix);

	List<GSStatsMatrix> getAllGsMatricList(Integer status);

	GSStatsMatrix getGsgsMatrixById(Integer id);

	GSStatsMatrix updateGsMatrix(GSStatsMatrix matrix);

}
