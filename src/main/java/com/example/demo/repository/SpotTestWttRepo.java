package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SpotTestWtt;

@Repository
public interface SpotTestWttRepo extends JpaRepository<SpotTestWtt, Long> {

	Optional<SpotTestWtt> getByTermId(Long termId);

	List<SpotTestWtt> findByStatus(Integer status);

}
