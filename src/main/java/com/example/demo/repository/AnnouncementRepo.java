package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Announcements;

@Repository
public interface AnnouncementRepo extends JpaRepository<Announcements, Long> {

	List<Announcements> findByStatusOrderByIdDesc(Integer status);

	List<Announcements> findAllByOrderByIdDesc();

	List<Announcements> findByStatusAndStatusNotInOrderByIdDesc(Integer status, Integer[] deletedStatus);

	List<Announcements> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
