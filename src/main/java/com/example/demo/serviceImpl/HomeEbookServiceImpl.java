package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ebook;
import com.example.demo.repository.HomeEbookRepository;
import com.example.demo.service.HomeEbookService;

@Service
public class HomeEbookServiceImpl implements HomeEbookService {

	@Autowired
	private HomeEbookRepository ebookRepo;

	@Override
	public Ebook addEbook(Ebook ebook) {
	//	if (ebook.getId() != null && ebook.getId() != 0) {
			ebook.setStatus(1);
			ebook.setCreatedAt(new Date());
			Ebook ebookData = ebookRepo.save(ebook);
			return ebookData;
	//	}
	//	return null;
	}

	@Override
	public Ebook getEbookById(Long id) {
		// TODO Auto-generated method stub
		if (id != null && id != 0) {
			Optional<Ebook> ebookData = ebookRepo.findById(id);
			if (ebookData.isPresent()) {
				return ebookData.get();
			}
		}
		return null;
	}

	@Override
	public List<Ebook> getAllEbook() {
		// TODO Auto-generated method stub
		Integer status[]= {2}; // for delete status =2
		List<Ebook> ebookData = ebookRepo.findAllByStatusNotIn(status);
		if (ebookData != null) {
			return ebookData;
		}
		return null;
	}

	@Override
	public Ebook updateEbook(Ebook ebook) {
		// TODO Auto-generated method stub
		if(ebook.getId()!=null && ebook.getId()!=null) {
			Optional<Ebook> ebookData = ebookRepo.findById(ebook.getId());
			if(ebookData.isPresent()){
				Ebook newEbook=ebookData.get();
				if(ebook.getName()!=null && !ebook.getName().isEmpty()){
					newEbook.setName(ebook.getName());
				}
				if(ebook.getDescription()!=null && !ebook.getDescription().isEmpty()){
					newEbook.setDescription(ebook.getDescription());
				}
				if(ebook.getEbookUrl()!=null && !ebook.getEbookUrl().isEmpty()){
					newEbook.setEbookUrl(ebook.getEbookUrl());
				}
				if(ebook.getStatus()!=null){
					newEbook.setStatus(ebook.getStatus());
				}
				newEbook.setUpdatedAt(new Date());
				newEbook=ebookRepo.save(newEbook);
				return newEbook;
			}
		}
		return null;
	}
}
