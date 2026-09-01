package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.StudyMaterial;

@Repository
public interface AdminStudyMaterialRepo extends JpaRepository<StudyMaterial, Long> {

	List<StudyMaterial> findAllByStatusAndStudyMaterialType(Integer one, String type);

	List<StudyMaterial> findAllByStatusAndStudyMaterialTypeOrderByIdDesc(Integer one, String type);

	@Query(value="SELECT * FROM ima_lms.study_material where study_material_type=?1 and status in(0,1) order by id desc",nativeQuery=true)
	List<StudyMaterial> findAllByStudyMaterialTypeOrderByIdDesc(String type);

	@Query(value="SELECT * FROM ima_lms.study_material where study_material_type=?1 and term_id=?2 and status in(0,1) order by id desc",nativeQuery=true)
	List<StudyMaterial> findAllByStudyMaterialTypeAndTermIdOrderByIdDesc(String type, Long termId);

	// studyMaterialType
}
