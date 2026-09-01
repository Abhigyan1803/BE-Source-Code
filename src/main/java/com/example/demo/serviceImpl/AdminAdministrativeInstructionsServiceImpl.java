package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AdministrativeInstructions;
import com.example.demo.repository.AdminAdministrativeInstructionsRepo;
import com.example.demo.service.AdminAdministrativeInstructionsService;

@Service
public class AdminAdministrativeInstructionsServiceImpl implements AdminAdministrativeInstructionsService {

	@Autowired
	AdminAdministrativeInstructionsRepo instructionRepo;

	@Override
	public AdministrativeInstructions createInstruction(AdministrativeInstructions instruction) {
		return instructionRepo.save(instruction);
	}

	@Override
	public List<AdministrativeInstructions> getAllInstructionsList(Integer status) {
		Integer[] deletedStatus = { 2 };
		if (status < 2) {
			List<AdministrativeInstructions> list = instructionRepo.findByStatusAndStatusNotInOrderByIdDesc(status,
					deletedStatus);
			return list;
		} else {
			List<AdministrativeInstructions> list = instructionRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
			return list;
		}
	}

	@Override
	public AdministrativeInstructions getInstructionById(Integer id) {
		Optional<AdministrativeInstructions> list = instructionRepo.findById(id);
		return list.get();
	}

	@Override
	public AdministrativeInstructions updateInstruction(AdministrativeInstructions instruction) {

		AdministrativeInstructions instr = null;
		Optional<AdministrativeInstructions> inst = instructionRepo.findById(instruction.getId());
		if (inst.isPresent()) {

			instr = inst.get();

			if (StringUtils.isNotBlank(instruction.getDoc())) {
				instr.setDoc(instruction.getDoc());
			}

			if (instruction.getName() != null) {

				instr.setName(instruction.getName());
			}

			if (instruction.getDescription() != null) {

				instr.setDescription(instruction.getDescription());
			}

			if (instruction.getStatus() != null) {

				instr.setStatus(instruction.getStatus());
			}

			instr.setUpdatedAt(new Date());

		}
		AdministrativeInstructions list = instructionRepo.save(instr);
		return list;
	}

}
