package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.LocationStateOfOffices;

@Repository
public interface LocationStateOfOfficesRepo extends JpaRepository<LocationStateOfOffices, Long> {

	Page<LocationStateOfOffices> findAllByOrderByIdDesc(Pageable pagedData);

	List<LocationStateOfOffices> findAllByOrderByIdDesc();

	List<LocationStateOfOffices> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
