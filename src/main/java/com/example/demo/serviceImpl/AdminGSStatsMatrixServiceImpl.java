package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.GSStatsMatrix;
import com.example.demo.repository.AdminGSStatsMatrixRepo;
import com.example.demo.service.AdminGSStatsMatrixService;

@Service
public class AdminGSStatsMatrixServiceImpl implements AdminGSStatsMatrixService {

	@Autowired
	AdminGSStatsMatrixRepo gsMatrixRepo;

	@Override
	public GSStatsMatrix createGsMatrix(GSStatsMatrix matrix) {
		return gsMatrixRepo.save(matrix);
	}

	@Override
	public List<GSStatsMatrix> getAllGsMatricList(Integer status) {
		Integer[] deletedStatus = { 2 };
		if (status < 2) {
			List<GSStatsMatrix> list = gsMatrixRepo.findByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
			return list;
		} else {
			List<GSStatsMatrix> list = gsMatrixRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
			return list;
		}
	}

	@Override
	public GSStatsMatrix getGsgsMatrixById(Integer id) {
		Optional<GSStatsMatrix> list = gsMatrixRepo.findById(id);
		return list.get();
	}

	@Override
	public GSStatsMatrix updateGsMatrix(GSStatsMatrix matrix) {
		GSStatsMatrix gsmatrix = null;
		Optional<GSStatsMatrix> m = gsMatrixRepo.findById(matrix.getId());
		if (m.isPresent()) {

			gsmatrix = m.get();

			if (StringUtils.isNotBlank(matrix.getDoc())) {
				gsmatrix.setDoc(matrix.getDoc());
			}

			if (matrix.getName() != null) {

				gsmatrix.setName(matrix.getName());
			}

			if (matrix.getDescription() != null) {

				gsmatrix.setDescription(matrix.getDescription());
			}

			if (matrix.getStatus() != null) {

				gsmatrix.setStatus(matrix.getStatus());
			}

			gsmatrix.setUpdatedAt(new Date());

		}
		GSStatsMatrix list = gsMatrixRepo.save(gsmatrix);
		return list;
	}

}
