package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BDO;

@Repository
@Transactional
public interface AdminBDORepo extends JpaRepository<BDO, Long> {

	List<BDO> findAllByStatus(Integer one);

	List<BDO> findAllByOrderByIdDesc();

	List<BDO> findByBattalianIdOrderByIdDesc(Integer id);

	List<BDO> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

	List<BDO> findByBattalianIdAndStatusNotInOrderByIdDesc(Integer id, Integer[] deletedStatus);

}
