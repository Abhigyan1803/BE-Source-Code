package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GsPolicyMisc;

@Repository
public interface AdminGsPolicyMiscRepo extends JpaRepository<GsPolicyMisc, Integer> {

	List<GsPolicyMisc> findByStatusOrderByIdDesc(Integer status);

	List<GsPolicyMisc> findAllByOrderByIdDesc();

	List<GsPolicyMisc> findByStatusAndStatusNotInOrderByIdDesc(Integer status, Integer[] deletedStatus);

	List<GsPolicyMisc> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
