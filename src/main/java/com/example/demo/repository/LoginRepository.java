package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AuthTable;

@Repository
public interface LoginRepository extends JpaRepository<AuthTable, Long> {

	AuthTable findByUsername(String username);

	@Query(value = "select * from auth_table where has_role not in(0,3)", nativeQuery = true)
	List<AuthTable> getStaffs();

	@Query(value = "select * from auth_table where status=1 and has_role not in(0,3)", nativeQuery = true)
	List<AuthTable> getActiveStaffs();

	AuthTable findByLoginId(long loginId);

}
