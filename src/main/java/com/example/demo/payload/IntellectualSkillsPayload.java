package com.example.demo.payload;

import java.util.List;

public class IntellectualSkillsPayload {
	private Integer totalRecords;
	private List<IntellectualSkillsFilterPayload> intellectualSkillsFilterPayload;

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<IntellectualSkillsFilterPayload> getIntellectualSkillsFilterPayload() {
		return intellectualSkillsFilterPayload;
	}

	public void setIntellectualSkillsFilterPayload(
			List<IntellectualSkillsFilterPayload> intellectualSkillsFilterPayload) {
		this.intellectualSkillsFilterPayload = intellectualSkillsFilterPayload;
	}
}
