package com.example.demo.service;

import java.util.List;

import com.example.demo.model.AdministrativeInstructions;

public interface AdminAdministrativeInstructionsService {

	AdministrativeInstructions createInstruction(AdministrativeInstructions instruction);

	List<AdministrativeInstructions> getAllInstructionsList(Integer status);

	AdministrativeInstructions getInstructionById(Integer id);

	AdministrativeInstructions updateInstruction(AdministrativeInstructions instruction);

}
