package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Ebook;

public interface HomeEbookService {

	Ebook addEbook(Ebook ebook);

	Ebook getEbookById(Long id);

	List<Ebook> getAllEbook();

	Ebook updateEbook(Ebook ebook);

}
