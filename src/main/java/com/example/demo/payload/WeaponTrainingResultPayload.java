package com.example.demo.payload;

import java.util.List;

public class WeaponTrainingResultPayload {
	private Integer totalRecords;
	private List<WeaponTrainingResultFilterPayload> weaponTrainingResultFilterPayload;
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<WeaponTrainingResultFilterPayload> getWeaponTrainingResultFilterPayload() {
		return weaponTrainingResultFilterPayload;
	}
	public void setWeaponTrainingResultFilterPayload(
			List<WeaponTrainingResultFilterPayload> weaponTrainingResultFilterPayload) {
		this.weaponTrainingResultFilterPayload = weaponTrainingResultFilterPayload;
	}
}
