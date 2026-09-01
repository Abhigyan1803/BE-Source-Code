package com.example.demo.payload;

public class OrganizationTeamMemberPayload {
	private Long team_member_id=0l;
	
	private String team_rank="";
	
	private String team_name="";
	
	private String team_position="";
	
	private String team_award="";
	
	private int team_status=0;
	
	

	public Long getTeam_member_id() {
		return team_member_id;
	}

	public void setTeam_member_id(Long team_member_id) {
		this.team_member_id = team_member_id;
	}

	public String getTeam_rank() {
		return team_rank;
	}

	public void setTeam_rank(String team_rank) {
		this.team_rank = team_rank;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public String getTeam_position() {
		return team_position;
	}

	public void setTeam_position(String team_position) {
		this.team_position = team_position;
	}

	public String getTeam_award() {
		return team_award;
	}

	public void setTeam_award(String team_award) {
		this.team_award = team_award;
	}

	public int getTeam_status() {
		return team_status;
	}

	public void setTeam_status(int team_status) {
		this.team_status = team_status;
	}

	
	

}
