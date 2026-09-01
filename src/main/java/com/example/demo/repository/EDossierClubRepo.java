package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EDossierClub;
import com.example.demo.service.EDossierClubService;

@Repository
public interface EDossierClubRepo extends JpaRepository<EDossierClub, Long> {

	EDossierClubService save(EDossierClubService service);

	Optional<EDossierClub> findById(String id);

	EDossierClubService findByserviceId(String serviceId);

	List<EDossierClub> findBystatus(Integer status);

	Optional<EDossierClub> findByServiceId(String serviceId);

	EDossierClub findByServiceIdAndTermId(String serviceId, Long termId);

}
