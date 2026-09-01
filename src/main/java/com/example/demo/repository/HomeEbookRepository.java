package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Ebook;

@Repository
public interface HomeEbookRepository extends JpaRepository<Ebook, Long>{
//@Query("select * from ebook where status not in (2), native=true")
	List<Ebook> findAllByStatusNotIn(Integer[] status);

}
