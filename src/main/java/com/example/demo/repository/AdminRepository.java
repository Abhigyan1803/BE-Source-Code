package com.example.demo.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Admin;

@Repository
@Transactional
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Admin findByUsernameAndIsDeleted(String username, Integer one);

	Admin findByUsernameAndPasswordAndIsDeleted(String username, String password, Integer one);

	Admin findByUsername(String username);

	@Query(value = "SELECT password FROM Admin where username=? ", nativeQuery = true)
	String getPassword(String username);

	Admin findByServiceIdAndPasswordAndIsDeleted(String serviceId, String password, int i);

	Optional<Admin> findByAdminId(long adminId);

}
