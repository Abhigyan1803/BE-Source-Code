package com.example.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Admin;
import com.example.demo.myexception.MyException;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.LoginService;
import com.example.demo.util.ConstantMessage;

@Service
public class LoginServiceImpl implements LoginService {

  
    @Autowired
    RoleRepository roleRepo;

    @Autowired
    LoginRepository  loginRepo;

	
   
	
   

    
}
