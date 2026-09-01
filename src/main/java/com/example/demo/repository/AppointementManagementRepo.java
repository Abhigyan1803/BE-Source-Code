package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AppointementManagement;

@Repository
public interface AppointementManagementRepo extends JpaRepository<AppointementManagement, Long> {

	List<AppointementManagement> findAllByRoleIdAndSubRoleId(Long roleId, Long subRoleId);

}
