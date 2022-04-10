package com.xuananh.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xuananh.demo.model.Student;
@Mapper
public interface StudentMapper extends IGenneralRepository<Student> {
	List<Student> findStudentByGradeId(Integer id);
}