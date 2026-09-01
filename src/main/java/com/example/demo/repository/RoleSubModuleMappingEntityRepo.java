package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.RoleSubModuleMappingEntity;

@Repository
public interface RoleSubModuleMappingEntityRepo extends JpaRepository<RoleSubModuleMappingEntity, Long> {

	@Transactional
	@Modifying
	@Query(value = "delete from role_sub_module_mapping where role_id=?1", nativeQuery = true)
	void deleteAllRoleSubModuleMapping(Long roleId);

	List<RoleSubModuleMappingEntity> findByRoleIdAndModuleId(Long id, Long id2);

}
