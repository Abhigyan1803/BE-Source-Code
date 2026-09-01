package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.HallOfFameOfficer;

@Repository
public interface HallOfFameOfficerRepo extends JpaRepository<HallOfFameOfficer, Integer> {

	List<HallOfFameOfficer> findByStatusOrderByIdDesc(Integer status);

	List<HallOfFameOfficer> findAllByOrderByIdDesc();

	List<HallOfFameOfficer> findByIsForeignAndAwardMedalAndStatusOrderByIdDesc(Integer fameCounrty, String awardName,
			Integer status);

	List<HallOfFameOfficer> findByStatusAndStatusNotInOrderByIdDesc(Integer status, Integer[] deletedStatus);

	List<HallOfFameOfficer> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

//	List<HallOfFameOfficer> findByIsForeignAndAwardMedalAndStatusOrderByIdDesc(int i, String awardName, int j);

}
