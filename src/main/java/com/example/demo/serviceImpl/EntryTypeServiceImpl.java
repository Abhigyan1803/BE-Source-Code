package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EntryType;
import com.example.demo.repository.EntryTypeRepo;
import com.example.demo.service.EntryTypeService;

@Service
public class EntryTypeServiceImpl implements EntryTypeService {

	@Autowired
	EntryTypeRepo entryTypeRepo;

	@Override
	public List<EntryType> getAllEntryTypeList() {
		List<EntryType> list = entryTypeRepo.findAllByStatus(1);
		return list;
	}

	@Override
	public EntryType createEntryType(EntryType entryType) {
		EntryType result = entryTypeRepo.save(entryType);
		return result;
	}

	@Override
	public EntryType updateEntryType(EntryType entryType) {
		EntryType entry = null;
		Optional<EntryType> et = entryTypeRepo.findById(entryType.getId());
		if (et.isPresent()) {
			entry = et.get();

			entry.setUpdatedAt(new Date());
			if (entryType.getType() != null && entryType.getType().trim().length() != 0) {
				entry.setType(entryType.getType());
			}

			if (entryType.getStatus() != null) {
				entry.setStatus(entryType.getStatus());
			}

		}
		return entryTypeRepo.save(entry);
	}

	@Override
	public EntryType getEntryTypeById(Long id) {
		Optional<EntryType> et = entryTypeRepo.findById(id);
		return et.get();
	}

	@Override
	public List<EntryType> getAllEntryTypeListByStatus(Integer status) {
		List<EntryType> list = null;
		if (status == 1) {
			list = entryTypeRepo.findByStatus(status);
		} else {
			list = entryTypeRepo.findAll();
		}

		return list;
	}

	@Override
	public EntryType isAlreadyExist(EntryType entryType) {
		return entryTypeRepo.findByType(entryType.getType());
	}

	@Override
	public EntryType validateEntryTypeExist(EntryType entryType) {
		EntryType et = entryTypeRepo.findByType(entryType.getType());
		if (et != null && et.getId() != entryType.getId()) {
			return et;
		}
		return null;
	}
}
