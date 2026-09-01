package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SubRoleManagement;

@Repository
public interface SubRoleManagementRepo extends JpaRepository<SubRoleManagement, Long> {

	List<SubRoleManagement> findByRoleId(Long roleId);

}
