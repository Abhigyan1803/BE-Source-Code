package com.example.demo.service;

import java.util.List;

import com.example.demo.model.EntryType;

public interface EntryTypeService {

	List<EntryType> getAllEntryTypeList();

	EntryType createEntryType(EntryType entryType);

	EntryType updateEntryType(EntryType entryType);

	EntryType getEntryTypeById(Long id);

	List<EntryType> getAllEntryTypeListByStatus(Integer status);

	EntryType isAlreadyExist(EntryType entryType);

	EntryType validateEntryTypeExist(EntryType entryType);

}
