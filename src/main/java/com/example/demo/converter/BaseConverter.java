package com.example.demo.converter;

import com.example.demo.entity.BaseEntity;
import com.example.demo.model.BaseModel;

public interface BaseConverter {

	public BaseModel convertEntityToModel(BaseEntity baseEntity);
    public BaseEntity convertModelToEntity(BaseModel baseModel);
}
