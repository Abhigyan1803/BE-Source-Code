package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Weapon;
import com.example.demo.model.WeaponAttributes;
import com.example.demo.repository.WeaponAttributesRepo;
import com.example.demo.repository.WeaponRepo;
import com.example.demo.service.WeaponService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ConstantVar;

@Service
public class WeaponServiceImpl implements WeaponService {

	@Autowired
	WeaponRepo weaponRepo;

	@Autowired
	WeaponAttributesRepo weaponAttributesRepo;

	@Override
	public Weapon createWeapon(Weapon weapon) {
		weapon.setStatus(ConstantVar.ONE);
		Weapon saveWeapon = weaponRepo.save(weapon);
		if (saveWeapon != null) {
			for (WeaponAttributes wa : saveWeapon.getWa()) {
				wa.setWeapon(saveWeapon);
				weaponAttributesRepo.save(wa);
			}
		}
		return saveWeapon;
	}

	@Override
	public Set<Weapon> getWeaponByTerm(Long termId, Integer status) {
		Set<Weapon> set = null;
		Set<Weapon> setWeapon = new HashSet<Weapon>();
		if (status == 1) {
			set = weaponRepo.findByStatusAndWaTermId(status, termId);
		} else {
			set = weaponRepo.findByWaTermIdOrderById(termId);
		}
		// System.out.println("set===>>" + set.toString());
		for (Weapon w : set) {
			Weapon weapon = new Weapon();
			weapon.setCreatedAt(w.getCreatedAt());
			weapon.setgPointIIITerm(w.getgPointIIITerm());
			weapon.setgPointIITerm(w.getgPointIITerm());
			weapon.setgPointITerm(w.getgPointITerm());
			weapon.setId(w.getId());
			weapon.setName(w.getName());
			weapon.setStatus(w.getStatus());
			weapon.setUpdatedAt(w.getUpdatedAt());
			List<WeaponAttributes> weaponAttributesList = new ArrayList<WeaponAttributes>();
			List<WeaponAttributes> wattList = w.getWa();
			int totalMaxMarks = 0;
			for (WeaponAttributes watt : wattList) {
				if (watt.getTermId().equals(termId)) {
					WeaponAttributes weaponAttributes = new WeaponAttributes();
					weaponAttributes.setId(watt.getId());
					weaponAttributes.setAttrName(watt.getAttrName());
					weaponAttributes.setCreatedAt(watt.getCreatedAt());
					weaponAttributes.setMaxMarks(watt.getMaxMarks());
					weaponAttributes.setTermId(termId);
					weaponAttributes.setUpdatedAt(watt.getUpdatedAt());
					weaponAttributes.setWeapon(weapon);
					totalMaxMarks += watt.getMaxMarks();
					weaponAttributesList.add(weaponAttributes);
				}

			}
			// w.setTotalMaxMarks(totalMaxMarks);
			weapon.setTotalMaxMarks(totalMaxMarks);
			weapon.setWa(weaponAttributesList);
			setWeapon.add(weapon);
		}

		return setWeapon;
	}

	@Override
	public List<Weapon> getWeaponByTermNew(Long termId, Integer status) {
		Set<Weapon> set = null;
		Set<Weapon> setWeapon = new HashSet<Weapon>();
		if (status == 1) {
			set = weaponRepo.findByStatusAndWaTermId(status, termId);
		} else {
			set = weaponRepo.findByWaTermIdOrderById(termId);
		}
		for (Weapon w : set) {
			Weapon weapon = new Weapon();
			weapon.setCreatedAt(w.getCreatedAt());
			weapon.setgPointIIITerm(w.getgPointIIITerm());
			weapon.setgPointIITerm(w.getgPointIITerm());
			weapon.setgPointITerm(w.getgPointITerm());
			weapon.setId(w.getId());
			weapon.setName(w.getName());
			weapon.setStatus(w.getStatus());
			weapon.setUpdatedAt(w.getUpdatedAt());
			List<WeaponAttributes> weaponAttributesList = new ArrayList<WeaponAttributes>();
			List<WeaponAttributes> wattList = w.getWa();
			int totalMaxMarks = 0;
			for (WeaponAttributes watt : wattList) {
				if (watt.getTermId().equals(termId)) {
					WeaponAttributes weaponAttributes = new WeaponAttributes();
					weaponAttributes.setId(watt.getId());
					weaponAttributes.setAttrName(watt.getAttrName());
					weaponAttributes.setCreatedAt(watt.getCreatedAt());
					weaponAttributes.setMaxMarks(watt.getMaxMarks());
					weaponAttributes.setTermId(termId);
					weaponAttributes.setUpdatedAt(watt.getUpdatedAt());
					weaponAttributes.setWeapon(weapon);
					totalMaxMarks += watt.getMaxMarks();
					weaponAttributesList.add(weaponAttributes);
				}

			}
			weapon.setTotalMaxMarks(totalMaxMarks);
			weapon.setWa(weaponAttributesList);
			setWeapon.add(weapon);
		}

		List<Weapon> listWeapon = new ArrayList<Weapon>();
		if (setWeapon.size() > 0) {
			for (Weapon weapon : setWeapon) {
				listWeapon.add(weapon);
			}
			Collections.sort(listWeapon, (w1, w2) -> {
				if (w1.getId() > w2.getId()) {
					return 1;
				} else {
					return -1;
				}
			});
			for (Weapon weapon : listWeapon) {
				List<WeaponAttributes> waList = weapon.getWa();
				Collections.sort(waList, (wa1, wa2) -> {
					if (wa1.getId() > wa2.getId()) {
						return 1;
					} else {
						return -1;
					}
				});
				// listWeapon.add(weapon);
			}
		}

		return listWeapon;
	}

	@Override
	public Weapon getWeaponById(Long id) {
		Optional<Weapon> list = weaponRepo.findById(id);
		return list.get();
	}

	@Override
	public Weapon updateWeapon(Weapon weapon) {
		List<WeaponAttributes> wp = new ArrayList<>();
		Weapon wep = null;
		Optional<Weapon> w = weaponRepo.findById(weapon.getId());
		if (w.isPresent()) {
			wep = w.get();

			if (weapon.getName() != null) {
				wep.setName(weapon.getName());
			}

			if (weapon.getgPointITerm() != null) {
				wep.setgPointITerm(weapon.getgPointITerm());
			}

			if (weapon.getgPointIITerm() != null) {
				wep.setgPointIITerm(weapon.getgPointIITerm());
			}

			if (weapon.getgPointIIITerm() != null) {
				wep.setgPointIIITerm(weapon.getgPointIIITerm());
			}

			if (weapon.getgPointIITech() != null) {
				wep.setgPointIITech(weapon.getgPointIITech());
			}

			if (weapon.getStatus() != null) {
				wep.setStatus(weapon.getStatus());
			}

			if (weapon.getWa() != null) {
				for (WeaponAttributes wa : weapon.getWa()) {
					wa.setWeapon(weapon);
					weaponAttributesRepo.save(wa);
					wp.add(wa);
				}
				wep.setWa(wp);
			}
		}
		return weaponRepo.save(wep);
	}

	@Override
	public String isWeaponExist(Weapon weapon) {
		String result = null;
		Weapon already_added = weaponRepo.findByName(weapon.getName());
		if (already_added != null) {
			return ConstantMessage.WEAPON_EXIST;
		}

		return result;
	}

	@Override
	public String isWeaponOrWaExist(Weapon weapon) {
		String result = null;
		Weapon already_added = weaponRepo.findByName(weapon.getName());
		if (already_added != null) {
			if (!already_added.getId().equals(weapon.getId())) {
				return ConstantMessage.WEAPON_EXIST;
			}
		}
		if (weapon.getWa() != null) {
			for (WeaponAttributes wa : weapon.getWa()) {
				if (wa.getId() != null && wa.getId() != 0) {
					WeaponAttributes weaponAttribute = weaponAttributesRepo
							.findByAttrNameAndTermIdAndWeapon(wa.getAttrName(), wa.getTermId(), weapon);
					if (weaponAttribute != null) {
						if (!wa.getId().equals(weaponAttribute.getId())) {
							return ConstantMessage.WEAPON_ATTRIBUTE_EXIST + " - " + weaponAttribute.getAttrName();
						}

					}
				}
			}
		}

		return result;
	}

}
