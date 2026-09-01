package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EDossierMenu;
import com.example.demo.model.EDossierSubMenu;
import com.example.demo.repository.EDossierMenuRepository;
import com.example.demo.repository.EDossierSubMenuRepository;
import com.example.demo.service.EDossierMenuService;

@Service
public class EDossierMenuServiceImpl implements EDossierMenuService {
	@Autowired
	private EDossierMenuRepository repo;

	@Autowired
	private EDossierSubMenuRepository subMenuRepo;

	@Override
	public List<EDossierMenu> findAllByStatus(Integer status) {
		List<EDossierMenu> list = repo.findAllByStatus(status);
		for (EDossierMenu menu : list) {
			List<EDossierSubMenu> subMenuList = subMenuRepo.findByMenuId(menu.getId());
			menu.setSubMenuList(subMenuList);
		}
		return list;
	}
}
