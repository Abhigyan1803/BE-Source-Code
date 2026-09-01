package com.example.demo.payload;

import com.example.demo.model.CampMarksResult;
import com.example.demo.model.RunbackRouteMr;

public class CampMarksRouteRunBack {

	private CampMarksResult campMarksResult;
	private RunbackRouteMr routeMarch;
	private RunbackRouteMr runback;

	public CampMarksResult getCampMarksResult() {
		return campMarksResult;
	}

	public void setCampMarksResult(CampMarksResult campMarksResult) {
		this.campMarksResult = campMarksResult;
	}

	public RunbackRouteMr getRouteMarch() {
		return routeMarch;
	}

	public void setRouteMarch(RunbackRouteMr routeMarch) {
		this.routeMarch = routeMarch;
	}

	public RunbackRouteMr getRunback() {
		return runback;
	}

	public void setRunback(RunbackRouteMr runback) {
		this.runback = runback;
	}

}
