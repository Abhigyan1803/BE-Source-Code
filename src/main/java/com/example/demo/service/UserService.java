package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletRequest;

import com.example.demo.model.User;
import com.example.demo.myexception.MyException;

public interface UserService {

	User createUser(User usr) throws MyException;

	User getdataByUsernameAndBattalionId(String trim, Integer battalionId, ServletRequest request) throws MyException;

	List<User> getAllUsers();

	Optional<User> getUserById(Integer id);

	User updateUser(User usr);

}
