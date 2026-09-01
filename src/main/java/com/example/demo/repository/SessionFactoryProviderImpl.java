package com.example.demo.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionFactoryProviderImpl implements SessionFactoryProvider{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public SessionFactory getSessionFactory() {
        return sessionFactory;
	}
}
