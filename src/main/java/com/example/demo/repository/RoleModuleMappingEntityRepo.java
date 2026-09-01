package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.RoleModuleMappingEntity;

@Repository
public interface RoleModuleMappingEntityRepo extends JpaRepository<RoleModuleMappingEntity, Long> {

	@Transactional
	@Modifying
	@Query(value = "delete from role_module_mapping where role_id=?1", nativeQuery = true)
	void deleteAllRoleModuleMapping(Long roleId);

	List<RoleModuleMappingEntity> findByRoleId(Long id);

	RoleModuleMappingEntity findByRoleIdAndModuleId(long parseLong, Long moduleId);

}
