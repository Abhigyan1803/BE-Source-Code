package com.example.demo.service;

import com.example.demo.model.EdDrillTerm3Dat;
public interface EdDrillTerm3DatService {

	EdDrillTerm3Dat findByServiceId(String serviceId);

	EdDrillTerm3Dat addEdDrillTerm3Dat(EdDrillTerm3Dat edDrillTerm3Dat);

	EdDrillTerm3Dat updateEdDrillTerm3Dat(EdDrillTerm3Dat edDrillTerm3Dat);

	EdDrillTerm3Dat findByServiceIdAndStatus(String serviceId, Integer status);

}
