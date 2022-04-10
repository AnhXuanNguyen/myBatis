package com.xuananh.demo.repository;

import java.util.List;

public interface IGenneralRepository<T> {
	List<T> findAll();
	T findById(Integer id);
	void save(T t);
	void deleteById(Integer id);
	void edit(T t);
}
