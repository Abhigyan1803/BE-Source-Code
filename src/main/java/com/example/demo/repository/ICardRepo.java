package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ICard;

@Repository
public interface ICardRepo extends JpaRepository<ICard, Long> {

	List<ICard> findAllByStatus(Integer status);

}
