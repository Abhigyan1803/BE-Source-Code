package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PtMotivationAwards;

@Repository
public interface PtMotivationAwardsRepository extends JpaRepository<PtMotivationAwards, Long> {

	PtMotivationAwards findByServiceIdAndTermId(String serviceId, Long termId);

}
