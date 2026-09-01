package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GC_Entitle;

@Repository
public interface GC_EntitleServiceRepo extends JpaRepository<GC_Entitle, Long> {
	List<GC_Entitle> findBytype(String type);

	List<GC_Entitle> findBytypeAndCadetId(String type, Long cadetId);

}
