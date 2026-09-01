package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ICard;

public interface ICardService {

	ICard createICard(ICard iCard);

	ICard getICardById(Long id);

	List<ICard> getICardByStatus(Integer status);

	ICard updateICard(ICard iCard);

	ICard updateICardStatus(ICard iCard);

}
