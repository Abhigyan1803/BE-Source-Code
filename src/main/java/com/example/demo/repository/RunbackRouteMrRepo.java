package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.RunbackRouteMr;

@Repository
@Transactional
public interface RunbackRouteMrRepo
		extends JpaRepository<RunbackRouteMr, Integer>, PagingAndSortingRepository<RunbackRouteMr, Integer> {

	Optional<RunbackRouteMr> findByServiceIdAndResultType(String serviceId, String resultType);

	List<RunbackRouteMr> getAllByStatus(Integer status);

	Optional<RunbackRouteMr> findById(Long id);

	Optional<RunbackRouteMr> findByServiceIdAndResultTypeAndTermId(String serviceId, String resultType, Long termId);

	List<RunbackRouteMr> findByServiceIdAndResultTypeOrderByTermId(String serviceId, String resultType);

}
