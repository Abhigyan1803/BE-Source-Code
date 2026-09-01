package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);

	User findByUsername(String username);

	@Query(value = "SELECT password FROM User where username=? ", nativeQuery = true)
	String getPassword(String username);

	
	User findByUsernameAndBattalianId(String username, Integer battalionId);//2may
	//User findByUsernameAndIsDeleted(String username, Integer zer0);//commented on second may and adding below method
//	User findByUsernameAndBattalianId(String username, Integer battalionId);// 2may
	// User findByUsernameAndIsDeleted(String username, Integer zer0);//commented on
	// second may and adding below method

	User findByUsernameAndIsDeletedAndStatus(String username, Integer zer0, Integer zer02);

	User findByServiceId(String serviceId);

	List<User> findAllByOrderByIdDesc();

}
