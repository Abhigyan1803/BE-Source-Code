package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.HomeAboutUs;

@Repository
public interface HomeAboutUsRepository extends JpaRepository<HomeAboutUs, Long> {

	List<HomeAboutUs> findAllByType(String type);

}
