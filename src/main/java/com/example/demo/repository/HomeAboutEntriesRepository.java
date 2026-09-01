package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.HomeAboutEntries;
@Repository
public interface HomeAboutEntriesRepository extends JpaRepository<HomeAboutEntries, Long>{

}
