package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserIdAndRoleManagement;

@Repository
public interface UserIdAndRoleManagementRepo  extends JpaRepository<UserIdAndRoleManagement, Long>{


	UserIdAndRoleManagement findAllByRoleIdAndSubRoleIdAndAppIdAndUserId(Long roleId, Long subRoleId, Long appId,
			Long userId);

	UserIdAndRoleManagement findByUserName(String userName);

	UserIdAndRoleManagement findByUserNameAndIsDeletedAndStatus(String username, Integer one, Integer one2);

}
