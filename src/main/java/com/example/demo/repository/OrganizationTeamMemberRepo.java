package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.OrganizationTeamMember;

@Repository
public interface OrganizationTeamMemberRepo extends JpaRepository<OrganizationTeamMember, Long>{

	List<OrganizationTeamMember> findByOrganizationChartIdAndStatus(Long id , int status);

}
