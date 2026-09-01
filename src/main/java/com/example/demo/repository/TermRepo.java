package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Term;

@Repository
public interface TermRepo extends JpaRepository<Term, Long> {

	@Query(value = "select * from term where id in (1,2,7) and status=1", nativeQuery = true)
	List<Term> getAllTermByIds();

	List<Term> findAllByStatusOrderBySeqNo(Integer status);

}
