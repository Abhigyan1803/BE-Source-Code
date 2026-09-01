package com.example.demo.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Weapon;

@Repository
public interface WeaponRepo extends JpaRepository<Weapon, Long> {

	Set<Weapon> findByWaTermIdOrderById(Long termId);

	Weapon findByName(String name);

	Set<Weapon> findByStatusAndWaTermId(Integer status, Long termId);

	Optional<Weapon> findById(Long weaponId);
}
