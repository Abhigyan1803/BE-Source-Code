package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.UserRoleModuleMappingEntity;

public interface UserRoleModuleMappingRepo extends JpaRepository<UserRoleModuleMappingEntity, Long> {

final String FIND_USER_ROLE_BY_ROLE_ID = "from UserRoleModuleMappingEntity where role_id = :roleId";
	
	@Query(FIND_USER_ROLE_BY_ROLE_ID)
	public List<UserRoleModuleMappingEntity> findByuserRoleId(Long roleId);
}
