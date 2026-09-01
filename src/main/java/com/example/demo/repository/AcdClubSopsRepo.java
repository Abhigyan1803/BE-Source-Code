package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AcdClubSops;

@Repository
public interface AcdClubSopsRepo extends JpaRepository<AcdClubSops, Long> {

	List<AcdClubSops> findAllByStatusOrderByIdDesc(Integer status);

	List<AcdClubSops> findAllByTypeAndStatusOrderByIdDesc(String type, Integer status);

	List<AcdClubSops> findAllByTypeAndSubTypeAndStatusOrderByIdDesc(String type, String subType, Integer status);

	List<AcdClubSops> findAllByTypeAndSubTypeAndSubSubTypeAndStatusOrderByIdDesc(String type, String subType,
			String subSubType, Integer status);

	List<AcdClubSops> findAllByOrderByIdDesc();

	List<AcdClubSops> findAllByTypeAndSubTypeAndSubSubTypeOrderByIdDesc(String type, String subType, String subSubType);
	
	@Query(value="SELECT * FROM ima_lms.acd_club_sops WHERE type=?1 AND sub_type=?2 AND status NOT IN (3) order by id", nativeQuery=true)
	List<AcdClubSops> findAllByTypeAndSubTypeOrderByIdDesc(String type, String subType);

	 @Query(value="SELECT * FROM ima_lms.acd_club_sops WHERE type=?1 AND status NOT IN (3) order by id", nativeQuery=true)
	List<AcdClubSops> findAllByTypeOrderByIdDesc(String type);
   
	 @Query(value="SELECT * FROM ima_lms.acd_club_sops WHERE status NOT IN (3) order by id", nativeQuery=true)
	List<AcdClubSops> findAllByStatusNotInOrderByIdDesc(Integer status);

}
