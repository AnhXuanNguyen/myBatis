package com.xuananh.demo.service.student;

import java.util.List;

import com.xuananh.demo.dto.StudentDto;
import com.xuananh.demo.dto.StudentResponse;
import com.xuananh.demo.model.Student;
import com.xuananh.demo.service.IGeneralService;

public interface IStudentService extends IGeneralService<Student> {
	List<StudentResponse> findAllStudent();
	void save(StudentDto studentDto);
	List<Student> findStudentByGradeId(Integer id);
}
