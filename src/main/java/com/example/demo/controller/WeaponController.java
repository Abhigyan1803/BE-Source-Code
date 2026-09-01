package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Weapon;
import com.example.demo.service.WeaponService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/weapon")
public class WeaponController {

	// @Value("${spring.url}")
	// private String url;
	//
	// @Value("${spring.dir}")
	// private String UploadDir;

	@Autowired
	WeaponService weaponService;

	@PostMapping(value = "/add-weapon")
	public ResponseEntity<?> addWeapon(@RequestBody Weapon weapon, ServletRequest request) {
		String alreadyExist = weaponService.isWeaponExist(weapon);
		if (alreadyExist == null) {
			Weapon response = weaponService.createWeapon(weapon);
			FileWritting.createLog((HttpServletRequest) request,
					response.getId() + ",add," + "weapon," + ConstantMessage.WEAPON_ADDED + "," + new Date());
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.WEAPON_ADDED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(alreadyExist, HttpStatus.OK, null), HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get-weapon-by-term")
	public ResponseEntity<?> getWeaponByTerm(@RequestParam Long termId, @RequestParam Integer status) {
		Set<Weapon> list = weaponService.getWeaponByTerm(termId, status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-weapon-by-term-new")
	public ResponseEntity<?> getWeaponByTermNew(@RequestParam Long termId, @RequestParam Integer status) {
		List<Weapon> list = weaponService.getWeaponByTermNew(termId, status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-weapon-by-id")
	public ResponseEntity<?> getWeaponById(@RequestParam Long id) {
		Weapon list = weaponService.getWeaponById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-weapon")
	public ResponseEntity<?> updateWeapon(@RequestBody Weapon weapon, ServletRequest request) {
		String alreadyExist = weaponService.isWeaponOrWaExist(weapon);
		System.out.println("alreadyExist====>>" + alreadyExist);
		if (alreadyExist == null) {
			Weapon response = weaponService.updateWeapon(weapon);
			FileWritting.createLog((HttpServletRequest) request,
					response.getId() + ",update," + "weapon," + ConstantMessage.WEAPON_UPDATED + "," + new Date());
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.WEAPON_UPDATED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(alreadyExist, HttpStatus.OK, null), HttpStatus.OK);
		}

	}

}
