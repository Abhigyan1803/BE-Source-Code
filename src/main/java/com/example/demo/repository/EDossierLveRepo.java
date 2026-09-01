package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EDossierLve;

@Repository
public interface EDossierLveRepo extends JpaRepository<EDossierLve, Long> {

	List<EDossierLve> findBystatus(Integer status);

	Optional<EDossierLve> findByServiceId(String serviceId);

	EDossierLve findByServiceIdAndTermId(String serviceId, Long termId);

}
