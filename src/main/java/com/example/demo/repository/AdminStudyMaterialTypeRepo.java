package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.StudyMaterialType;

@Repository
public interface AdminStudyMaterialTypeRepo extends JpaRepository<StudyMaterialType, Long> {
	List <StudyMaterialType> findAllByStatus(Integer one);
	@Query(value="SELECT * FROM ima_lms.study_material order by id desc",nativeQuery=true)
	List <StudyMaterialType> findAllOrderByIdDesc();
}
