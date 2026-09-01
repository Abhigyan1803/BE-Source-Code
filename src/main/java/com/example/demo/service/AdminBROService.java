package com.example.demo.service;

import java.util.List;
import java.util.Set;

import com.example.demo.model.BRO;
import com.example.demo.model.BRODocuments;
import com.example.demo.myexception.MyException;

public interface AdminBROService {

	BRO createBRO(BRO bro, Set<BRODocuments> brodocs) throws MyException;

	List<BRO> getAllBROList(String status);

	BRO getBROById(Long id);

	BRO updateBRO(BRO bro, Set<BRODocuments> brodocs);

}
