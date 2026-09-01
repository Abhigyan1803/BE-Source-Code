package com.example.demo.payload;

import java.util.List;

import com.example.demo.model.OrganizationChart;
import com.example.demo.model.OrganizationTeamMember;

public class OrganizationMemberResponsePayload {
	
	OrganizationChart organizationChart;
	
	List<OrganizationTeamMember> memberList;

	public OrganizationChart getOrganizationChart() {
		return organizationChart;
	}

	public void setOrganizationChart(OrganizationChart organizationChart) {
		this.organizationChart = organizationChart;
	}

	public List<OrganizationTeamMember> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<OrganizationTeamMember> memberList) {
		this.memberList = memberList;
	}
	
	

}
