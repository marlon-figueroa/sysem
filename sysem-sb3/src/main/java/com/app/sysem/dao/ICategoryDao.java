package com.app.sysem.dao;

import org.springframework.data.repository.CrudRepository;

import com.app.sysem.model.Category;

public interface ICategoryDao extends CrudRepository<Category, Long> {

}
