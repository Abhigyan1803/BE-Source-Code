package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.VehicleStickers;

@Repository
public interface VehicleStickersRepo extends JpaRepository<VehicleStickers, Long> {

	List<VehicleStickers> findAllByStatusOrderByIdDesc(int status);

	List<VehicleStickers> findAllByOrderByIdDesc();

}
