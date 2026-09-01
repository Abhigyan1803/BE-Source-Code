package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Withdrawal;
import com.example.demo.repository.AdminWithdrawalRepo;
import com.example.demo.service.AdminWithdrawalService;

@Service
public class AdminWithdrawalServiceImpl implements AdminWithdrawalService {

	@Autowired
	AdminWithdrawalRepo withdrawalRepo;

	@Override
	public Withdrawal createWithdrawal(Withdrawal withdrawal) {
		return withdrawalRepo.save(withdrawal);
	}

	@Override
	public List<Withdrawal> getAllWithdrawalList(Integer status) {
		if (status < 2) {
			List<Withdrawal> list = withdrawalRepo.findByStatusOrderByIdDesc(status);
			return list;
		}

		else {
			List<Withdrawal> list = withdrawalRepo.findAllByOrderByIdDesc();
			return list;
		}
	}

	@Override
	public Withdrawal getWithdrawalById(Integer id) {
		Optional<Withdrawal> list = withdrawalRepo.findById(id);
		return list.get();
	}

	@Override
	public Withdrawal updateWithdrawal(Withdrawal withdrawal) {
		Withdrawal withdraw = null;
		Optional<Withdrawal> w = withdrawalRepo.findById(withdrawal.getId());
		if (w.isPresent()) {

			withdraw = w.get();

			if (StringUtils.isNotBlank(withdrawal.getDoc())) {
				withdraw.setDoc(withdrawal.getDoc());
			}

			if (withdrawal.getName() != null) {

				withdraw.setName(withdrawal.getName());
			}

			if (withdrawal.getDescription() != null) {

				withdraw.setDescription(withdrawal.getDescription());
			}

			if (withdrawal.getStatus() != null) {

				withdraw.setStatus(withdrawal.getStatus());
			}

			withdraw.setUpdatedAt(new Date());

		}
		Withdrawal list = withdrawalRepo.save(withdraw);
		return list;
	}

}
