package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AssaigmentOfDuties;

@Repository
public interface AssaigmentOfDutiesRepo extends JpaRepository<AssaigmentOfDuties, Long> {
	
	@Query(value="SELECT * FROM ima_lms.assaigment_of_duties where status in(0,1) order by id", nativeQuery=true)
	List<AssaigmentOfDuties> findAllByOrderByIdDesc();
	
	@Query(value="SELECT * FROM ima_lms.assaigment_of_duties where battalion_type_id=?1 AND status in(0,1) order by id", nativeQuery=true)
	List<AssaigmentOfDuties> findByBattalionTypeIdOrderByIdDesc(int battalionId);

	List<AssaigmentOfDuties> findByBattalionTypeIdAndStatusOrderByIdDesc(int battalionId, int status);

	List<AssaigmentOfDuties> findAllByStatusOrderByIdDesc(int status);

	

}
