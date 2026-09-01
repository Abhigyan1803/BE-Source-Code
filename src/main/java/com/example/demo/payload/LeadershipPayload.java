package com.example.demo.payload;

import java.util.List;

public class LeadershipPayload {

	private Integer totalRecords;
	private List<LeadershipFilterPayload> leadershipFilterPayload;

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<LeadershipFilterPayload> getLeadershipFilterPayload() {
		return leadershipFilterPayload;
	}

	public void setLeadershipFilterPayload(List<LeadershipFilterPayload> leadershipFilterPayload) {
		this.leadershipFilterPayload = leadershipFilterPayload;
	}

}
