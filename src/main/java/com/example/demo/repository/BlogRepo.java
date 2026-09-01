package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Blog;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Long> {

	List<Blog> findByStatusOrderByIdDesc(Integer status);

	List<Blog> findAllByOrderByIdDesc();

	List<Blog> findByCategoryAndStatusOrderByIdDesc(String cat, int i);

	List<Blog> findByStatusAndStatusNotInOrderByIdDesc(Integer status, Integer[] deletedStatus);

	List<Blog> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

	List<Blog> findByStatusAndStatusNotInOrderByIdDesc(Integer status, Integer[] deletedStatus, Pageable pageable);

	List<Blog> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus, Pageable pageable);

	List<Blog> findByBattalionIdAndStatusAndStatusNotInOrderByIdDesc(Integer battalionId, Integer status,
			Integer[] deletedStatus, Pageable pageable);

	List<Blog> findByBattalionIdAndStatusAndStatusNotInOrderByIdDesc(Integer battalionId, Integer status,
			Integer[] deletedStatus);

	List<Blog> findAllByBattalionIdAndStatusNotInOrderByIdDesc(Integer battalionId, Integer[] deletedStatus,
			Pageable pageable);

	List<Blog> findAllByBattalionIdAndStatusNotInOrderByIdDesc(Integer battalionId, Integer[] deletedStatus);

}
