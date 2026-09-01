package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.RequirementComplaint;

@Repository
public interface RequirementComplaintRepo extends JpaRepository<RequirementComplaint, Long> {

	List<RequirementComplaint> findAllByStatusOrderByIdDesc(int status);

	List<RequirementComplaint> findAllByOrderByIdDesc();

	List<RequirementComplaint> findByRequestNatureAndStatus(String requestNature, int status);

	List<RequirementComplaint> findAllByStatusAndStatusNotInOrderByIdDesc(int status, Integer[] deletedStatus);

	List<RequirementComplaint> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
