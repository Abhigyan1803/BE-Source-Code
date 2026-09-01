package com.example.demo.service;

import com.example.demo.model.EdEqtn;

public interface EdEqtnService {

	EdEqtn addEdEqtn(EdEqtn edEqtn);

	EdEqtn getByServiceId(String serviceId);

	EdEqtn updateEdEqtn(EdEqtn edEqtn);

}
