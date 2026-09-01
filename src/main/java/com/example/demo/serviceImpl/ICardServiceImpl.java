package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ICard;
import com.example.demo.repository.ICardRepo;
import com.example.demo.service.ICardService;

@Service
public class ICardServiceImpl implements ICardService {

	@Autowired
	private ICardRepo iCardRepo;

	@Override
	public ICard createICard(ICard iCard) {
		// TODO Auto-generated method stub
		ICard icard = iCardRepo.save(iCard);
		return icard;
	}

	@Override
	public ICard getICardById(Long id) {
		// TODO Auto-generated method stub
		Optional<ICard> icard = iCardRepo.findById(id);
		return icard.get();
	}

	@Override
	public List<ICard> getICardByStatus(Integer status) {
		// TODO Auto-generated method stub
		List<ICard> list = null;
		if (status == 1) {
			list = iCardRepo.findAllByStatus(status);
		} else {
			list = iCardRepo.findAll();
		}
		return list;

	}

	@Override
	public ICard updateICard(ICard iCard) {
		ICard result = null;
		if (iCard != null && iCard.getId() != null && iCard.getId() != 0) {
			Optional<ICard> icard = iCardRepo.findById(iCard.getId());
			if (icard.isPresent()) {
				iCard.setCreatedAt(icard.get().getCreatedAt());
				iCard.setUpdatedAt(new Date());
				result = iCardRepo.save(iCard);
			}
		}
		return result;
	}

	@Override
	public ICard updateICardStatus(ICard iCard) {
		// TODO Auto-generated method stub
		Optional<ICard> icard = iCardRepo.findById(iCard.getId());
		if (icard.isPresent()) {
			if (iCard.getStatus() != null) {
				icard.get().setStatus(iCard.getStatus());
				icard.get().setUpdatedAt(new Date());
			}
		}
		ICard result = iCardRepo.save(icard.get());
		return result;
	}

}
