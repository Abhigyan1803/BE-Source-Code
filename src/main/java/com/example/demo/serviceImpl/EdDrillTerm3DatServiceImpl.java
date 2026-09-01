package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EdDrillTerm3Dat;
import com.example.demo.repository.EdDrillTerm3DatRepository;
import com.example.demo.service.EdDrillTerm3DatService;

@Service
public class EdDrillTerm3DatServiceImpl implements EdDrillTerm3DatService{

	@Autowired
	private EdDrillTerm3DatRepository edDrillTerm3DatRepository;
	
	@Override
	public EdDrillTerm3Dat findByServiceId(String serviceId) {
		// TODO Auto-generated method stub
		return edDrillTerm3DatRepository.findByServiceId(serviceId);
	}
	
	@Override
	public EdDrillTerm3Dat findByServiceIdAndStatus(String serviceId,Integer status) {
		// TODO Auto-generated method stub
		return edDrillTerm3DatRepository.findByServiceIdAndStatus(serviceId,status);
	}
	
	@Override
	public EdDrillTerm3Dat addEdDrillTerm3Dat(EdDrillTerm3Dat edDrillTerm3Dat) {
		// TODO Auto-generated method stub
		edDrillTerm3Dat.setCreatedAt(new Date());
		return edDrillTerm3DatRepository.save(edDrillTerm3Dat);
	}
	@Override
	public EdDrillTerm3Dat updateEdDrillTerm3Dat(EdDrillTerm3Dat edDrillTerm3Dat) {
		// TODO Auto-generated method stub
		EdDrillTerm3Dat edDrill=null;
		if(edDrillTerm3Dat !=null && edDrillTerm3Dat.getId()!=null && edDrillTerm3Dat.getId()!=0) {
			
			Optional<EdDrillTerm3Dat> awards=edDrillTerm3DatRepository.findById(edDrillTerm3Dat.getId());
			if(awards.isPresent()) {
				edDrill=awards.get();
				
				if (edDrillTerm3Dat.getA() != null) {

					edDrill.setA(edDrillTerm3Dat.getA());
				}
				if (edDrillTerm3Dat.getB() != null) {

					edDrill.setB(edDrillTerm3Dat.getB());
				}
				if (edDrillTerm3Dat.getC() != null) {

					edDrill.setC(edDrillTerm3Dat.getC());
				}
				if (edDrillTerm3Dat.getD() != null) {

					edDrill.setD(edDrillTerm3Dat.getD());
				}
				if (edDrillTerm3Dat.getE() != null) {

					edDrill.setE(edDrillTerm3Dat.getE());
				}
				edDrill.setUpdatedAt(new Date());
			}
			edDrill=edDrillTerm3DatRepository.save(edDrill);
		}
		return edDrill;

	}
}
