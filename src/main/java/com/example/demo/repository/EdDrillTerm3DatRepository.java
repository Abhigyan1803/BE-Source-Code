package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EdDrillTerm3Dat;

@Repository
public interface EdDrillTerm3DatRepository extends JpaRepository<EdDrillTerm3Dat, Long> {

	EdDrillTerm3Dat findByServiceId(String serviceId);

	EdDrillTerm3Dat findByServiceIdAndStatus(String serviceId, Integer status);

}
