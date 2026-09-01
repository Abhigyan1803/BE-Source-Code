package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Weapon;
import com.example.demo.model.WeaponAttributes;

@Repository
public interface WeaponAttributesRepo extends JpaRepository<WeaponAttributes, Long> {

	List<WeaponAttributes> findByTermId(Integer termId);

	WeaponAttributes findByAttrNameAndTermId(String attrName, Long termId);

	WeaponAttributes findByAttrNameAndTermIdAndWeapon(String attrName, Long termId, Weapon weapon);

	WeaponAttributes findById(Integer id);

}
