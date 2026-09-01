package com.example.demo.service;


import java.util.Set;

import com.example.demo.model.OqSubjectDetails;

public interface OqSubjectDetailsService {

	OqSubjectDetails createSubject(OqSubjectDetails oqSubjectDetails);

	Set<OqSubjectDetails> getSubjectByTerm(Long termId);

	OqSubjectDetails getSubjectById(Long id);

	OqSubjectDetails updateSubject(OqSubjectDetails oqSubjectDetails);
}
