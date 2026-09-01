package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.OqMarksResult;

@Repository
public interface OqMarksResultRepo extends JpaRepository<OqMarksResult, Long> {

	OqMarksResult findByServiceIdAndTermIdAndEntryTypeId(String serviceId, int termId, long entryTypeId);

	@Query(value = "SELECT * FROM ima_lms.oq_marks_result where service_id=?1 order by term_id asc,entry_type_id", nativeQuery = true)
	List<OqMarksResult> getByServiceIdOrderByTermIdAndEntryTypeId(String serviceId);

	List<OqMarksResult> findByServiceIdAndEntryTypeId(String serviceId, Long entryTypeId);

}
