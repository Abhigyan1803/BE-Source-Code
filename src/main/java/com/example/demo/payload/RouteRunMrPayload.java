package com.example.demo.payload;

import java.util.List;

public class RouteRunMrPayload {

	private Integer totalRecords;
	private List<RouteRunMrFilterPayload> routeRunMrFilterPayload;

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<RouteRunMrFilterPayload> getRouteRunMrFilterPayload() {
		return routeRunMrFilterPayload;
	}

	public void setRouteRunMrFilterPayload(List<RouteRunMrFilterPayload> routeRunMrFilterPayload) {
		this.routeRunMrFilterPayload = routeRunMrFilterPayload;
	}

}
