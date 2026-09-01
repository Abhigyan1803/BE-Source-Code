package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BRO;

@Repository
@Transactional
public interface AdminBRORepo extends JpaRepository<BRO, Long> {

	List<BRO> findAllByStatus(Integer one);

	List<BRO> findAllByOrderByIdDesc();

	// List<BRO> findByBattalianIdByOrderByIdDesc(Integer id);

	List<BRO> findByBattalianIdOrderByIdDesc(Integer id);

	BRO findByBroNumber(String broNumber);

	List<BRO> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

	List<BRO> findByBattalianIdAndStatusNotInOrderByIdDesc(Integer id, Integer[] deletedStatus);

}
