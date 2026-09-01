package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserIdManagement;

@Repository
public interface UserIdManagementRepo extends JpaRepository<UserIdManagement, Long> {

	List<UserIdManagement> findAllByRoleIdAndSubRoleIdAndAppId(Long roleId, Long subRoleId, Long appId);

}
