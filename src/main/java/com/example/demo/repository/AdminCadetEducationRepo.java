package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CadetEducation;

@Repository
@Transactional
public interface AdminCadetEducationRepo
		extends JpaRepository<CadetEducation, Long>, PagingAndSortingRepository<CadetEducation, Long> {

}
