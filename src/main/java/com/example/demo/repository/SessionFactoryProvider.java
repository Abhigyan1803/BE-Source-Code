package com.example.demo.repository;

import org.hibernate.SessionFactory;

public interface SessionFactoryProvider {

	public SessionFactory getSessionFactory();
}
