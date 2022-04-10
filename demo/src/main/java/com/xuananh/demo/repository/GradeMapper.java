package com.xuananh.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.xuananh.demo.model.Grade;
@Mapper
public interface GradeMapper extends IGenneralRepository<Grade> {
	Grade findByStudentId(Integer studentId);
}