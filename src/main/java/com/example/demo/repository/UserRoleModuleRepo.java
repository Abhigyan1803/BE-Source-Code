package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserRoleEntity;
import com.example.demo.model.UserRoleModel;

@Repository
@Transactional
public interface UserRoleModuleRepo extends JpaRepository<UserRoleEntity, Long>{

	final String UPDATE_USER_ROLE = "UPDATE UserRoleEntity SET roleName = :roleName, roleId = :roleId, description = :description, status = :status, is_show = :isShow, modified_date = current_date() where id = :id";
	
	final String FIND_USER_ROLE_BY_NAME = "from UserRoleEntity where roleName = :roleName";
	@Modifying
	@Query(UPDATE_USER_ROLE)
	public Integer updateUserRole(String roleName, String roleId, String description, Integer status, boolean isShow, Long id);
	
	@Query(FIND_USER_ROLE_BY_NAME)
	public UserRoleEntity findByRoleName(String roleName);
}
