package com.example.demo.payload;

import java.util.List;

public class OrganizationChartPayload {
	private Long orgId;
	
    private String name="";
	
	private String award="";
	
	private String rank="";
	
	private Long position;
	
	private int status=0;
	
	List<OrganizationTeamMemberPayload> teamMembers;
	
	

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAward() {
		return award;
	}

	public void setAward(String award) {
		this.award = award;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<OrganizationTeamMemberPayload> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<OrganizationTeamMemberPayload> teamMembers) {
		this.teamMembers = teamMembers;
	}
   
	
	

}
