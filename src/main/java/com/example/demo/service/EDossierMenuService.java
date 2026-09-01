package com.example.demo.service;

import java.util.List;

import com.example.demo.model.EDossierMenu;

public interface EDossierMenuService {

	List<EDossierMenu> findAllByStatus(Integer status);

}
