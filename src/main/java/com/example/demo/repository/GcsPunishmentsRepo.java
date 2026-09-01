package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GcsPunishments;

@Repository
public interface GcsPunishmentsRepo extends JpaRepository<GcsPunishments, Long> {

	List<GcsPunishments> findAllByServiceIdAndTermIdAndStatusNotIn(String serviceId, Long termId,
			Integer[] deletedStatus);

	List<GcsPunishments> findAllByServiceIdAndTermIdAndStatusAndStatusNotIn(String serviceId, Long termId, int i,
			Integer[] deletedStatus);

	List<GcsPunishments> findAllByServiceIdAndStatusAndStatusNotIn(String serviceId, int i, Integer[] deletedStatus);

	List<GcsPunishments> findAllByServiceIdAndStatusNotIn(String serviceId, Integer[] deletedStatus);

}
