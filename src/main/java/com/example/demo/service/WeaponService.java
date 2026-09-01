package com.example.demo.service;

import java.util.List;
import java.util.Set;

import com.example.demo.model.Weapon;

public interface WeaponService {

	Weapon createWeapon(Weapon weapon);

	Set<Weapon> getWeaponByTerm(Long termId, Integer status);

	Weapon getWeaponById(Long id);

	Weapon updateWeapon(Weapon weapon);

	String isWeaponExist(Weapon weapon);

	String isWeaponOrWaExist(Weapon weapon);

	List<Weapon> getWeaponByTermNew(Long termId, Integer status);

}
