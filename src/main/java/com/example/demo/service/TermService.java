package com.example.demo.service;

import java.util.Map;

import com.example.demo.model.Term;

public interface TermService {

	Map<Object, Object> getAllTerms();

	Term createTerm(Term term);

	Term updateTerm(Term term);

	Term getTermById(Long id);

	Map<Object, Object> getAllTermsNew();

}
