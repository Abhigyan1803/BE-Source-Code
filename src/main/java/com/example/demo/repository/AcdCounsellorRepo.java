package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AcdCounsellor;

@Repository
public interface AcdCounsellorRepo extends JpaRepository<AcdCounsellor, Long> {

	List<AcdCounsellor> findAllByBattalionIdAndCompanyIdAndStatusOrderByIdDesc(Long battalionId, Long companyId,
			Integer status);

	List<AcdCounsellor> findAllByStatusOrderByIdDesc(Integer status);

	List<AcdCounsellor> findAllByBattalionIdAndCompanyIdOrderByIdDesc(Long battalionId, Long companyId);

	List<AcdCounsellor> findAllByOrderByIdDesc();

}
