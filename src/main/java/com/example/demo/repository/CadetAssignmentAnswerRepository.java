package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CadetAssignmentAnswer;

@Repository
public interface CadetAssignmentAnswerRepository extends JpaRepository<CadetAssignmentAnswer, Long> {

	List<CadetAssignmentAnswer> findAllByServiceIdAndStatusOrderByIdDesc(String serviceId, Integer status);

	List<CadetAssignmentAnswer> findAllByServiceIdOrderByIdDesc(String serviceId);

	List<CadetAssignmentAnswer> findAllByStatusOrderByIdDesc(Integer status);

	List<CadetAssignmentAnswer> findAllByAcdAsnIdAndStatusOrderByIdDesc(Long acdAsnId, Integer status);

	List<CadetAssignmentAnswer> findAllByAcdAsnIdOrderByIdDesc(Long acdAsnId);

	Optional<CadetAssignmentAnswer> findByAcdAsnIdAndServiceId(Long id, String serviceId);

}
