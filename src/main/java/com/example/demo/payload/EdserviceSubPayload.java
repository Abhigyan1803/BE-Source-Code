package com.example.demo.payload;

import com.example.demo.model.GSO2ServiceSubjectBMTResult;
import com.example.demo.model.RunbackRouteMr;
import com.example.demo.model.ServiceBmt2Result;

public class EdserviceSubPayload {
	private GSO2ServiceSubjectBMTResult bmt1;
	private ServiceBmt2Result bmt2;
	private RunbackRouteMr mrPrac;

	public GSO2ServiceSubjectBMTResult getBmt1() {
		return bmt1;
	}

	public void setBmt1(GSO2ServiceSubjectBMTResult bmt1) {
		this.bmt1 = bmt1;
	}

	public ServiceBmt2Result getBmt2() {
		return bmt2;
	}

	public void setBmt2(ServiceBmt2Result bmt2) {
		this.bmt2 = bmt2;
	}

	public RunbackRouteMr getMrPrac() {
		return mrPrac;
	}

	public void setMrPrac(RunbackRouteMr mrPrac) {
		this.mrPrac = mrPrac;
	}

}
