package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AcademicLeadershipSubject;

@Repository
public interface AcademicLeadershipSubjectRepository extends JpaRepository<AcademicLeadershipSubject, Long> {

	List<AcademicLeadershipSubject> findBystatus(Integer status);

	List<AcademicLeadershipSubject> findBystatusOrderByIdDesc(Integer status);

}
