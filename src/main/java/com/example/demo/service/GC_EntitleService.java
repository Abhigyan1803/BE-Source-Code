package com.example.demo.service;

import java.util.List;

import com.example.demo.model.GC_Entitle;

public interface GC_EntitleService {
	public List<GC_Entitle> getGC_Entitle(String type, Long cadetId);

	public GC_Entitle saveGC_Entitle(GC_Entitle entitle);
}
