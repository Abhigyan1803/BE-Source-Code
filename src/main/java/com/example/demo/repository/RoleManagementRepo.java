package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.RoleManagement;

@Repository
public interface RoleManagementRepo extends JpaRepository<RoleManagement, Long> {

	List<RoleManagement> findAllByIsShow(int isShow);


}
