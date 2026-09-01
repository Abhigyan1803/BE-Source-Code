package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ModulesEntity;
import com.example.demo.model.ModulesModel;
import com.example.demo.payload.ModulesEntityPayLoad;

public interface ModulesService {

	public ModulesModel addModules(ModulesModel module);

	public ModulesEntity getById(Long id);

	public List<ModulesEntityPayLoad> getModuleList();

	public ModulesEntity updateModule(ModulesEntity modules);

}
