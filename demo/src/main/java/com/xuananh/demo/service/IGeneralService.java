package com.xuananh.demo.service;

import java.util.List;
import java.util.Optional;

public interface IGeneralService<T> {
	List<T> findAll();
	Optional<T> findById(Integer id);
	void save(T t);
	void deleteById(Integer id);
}
