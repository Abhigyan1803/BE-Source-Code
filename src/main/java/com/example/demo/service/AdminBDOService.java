package com.example.demo.service;

import java.util.List;
import java.util.Set;

import com.example.demo.model.BDO;
import com.example.demo.model.BDODocuments;

public interface AdminBDOService {

	BDO createBDO(BDO bdo, Set<BDODocuments> bdodocs);

	List<BDO> getAllBDOList(String status);

	BDO getBDOById(Long id);

	BDO updateBDO(BDO bdo, Set<BDODocuments> bdodocs);

}
