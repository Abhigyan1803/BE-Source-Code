package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EDossierSubMenu;

@Repository
public interface EDossierSubMenuRepository extends JpaRepository<EDossierSubMenu, Long> {

	List<EDossierSubMenu> findByMenuId(Long id);

}
