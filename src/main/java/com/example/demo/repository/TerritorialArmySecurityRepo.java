package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TerritorialArmySecurity;

@Repository
public interface TerritorialArmySecurityRepo extends JpaRepository<TerritorialArmySecurity, Long> {

	List<TerritorialArmySecurity> findAllByStatusOrderByIdDesc(int status);

	List<TerritorialArmySecurity> findAllByOrderByIdDesc();

	List<TerritorialArmySecurity> findAllByStatusAndStatusNotInOrderByIdDesc(int status, Integer[] deletedStatus);

	List<TerritorialArmySecurity> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
