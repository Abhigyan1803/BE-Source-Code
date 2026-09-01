package com.example.demo.service;

import java.util.List;

import com.example.demo.model.StandingTRGDirectives;

public interface AdminStandingTRGDirectivesService {

	StandingTRGDirectives createTRGDirectives(StandingTRGDirectives trgDirective);

	List<StandingTRGDirectives> getAllTRGDirectivesList(Integer status);

	StandingTRGDirectives getTRGDirectivesById(Integer id);

	StandingTRGDirectives updateTRGDirective(StandingTRGDirectives trgDirective);

}
