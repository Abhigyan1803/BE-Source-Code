package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Withdrawal;

public interface AdminWithdrawalService {

	Withdrawal createWithdrawal(Withdrawal withdrawal);

	List<Withdrawal> getAllWithdrawalList(Integer status);

	Withdrawal getWithdrawalById(Integer id);

	Withdrawal updateWithdrawal(Withdrawal withdrawal);

}
