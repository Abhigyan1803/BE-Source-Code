package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.BattallionActivity;

public interface BattalionActivityRepo extends JpaRepository<BattallionActivity,Long>{

//	List<BattallionActivity> findByStatusOrderByCreayedD(int status);

	List<BattallionActivity> findByStatusOrderByIdDesc(int status);
}
