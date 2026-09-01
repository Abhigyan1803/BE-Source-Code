package com.example.demo.repository;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public interface Dao<T> {

	Long save(T obj);
    
    void saveAll(List<T> obj);

    void saveOrUpdate(T obj);

    T merge(T obj);

    void delete(T obj);

    void deleteAll();

    void deleteAllById(List<Long> ids);

    void deleteAll(List<T> objects);

    void update(T obj);

    void updateAll(List<T> obj);

    List<T> loadAll();

    T getById(Serializable id);

    void deleteById(Serializable id);

    CriteriaBuilder getCriteriaBuilder();

    Query<T> createTypedCriteriaQuery(CriteriaQuery<T> typedCriteriaQuery);

    void flush();

}