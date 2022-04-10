package com.xuananh.demo.service.student;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuananh.demo.dto.StudentDto;
import com.xuananh.demo.dto.StudentResponse;
import com.xuananh.demo.model.Grade;
import com.xuananh.demo.model.Student;
import com.xuananh.demo.repository.GradeMapper;
import com.xuananh.demo.repository.StudentMapper;
@Service
public class StudentService implements IStudentService {
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private GradeMapper gradeMapper;

	@Override
	public List<Student> findAll() {
		return studentMapper.findAll();
	}

	@Override
	public Optional<Student> findById(Integer id) {
		Student student = studentMapper.findById(id);
		Optional<Student> studentOptional = Optional.ofNullable(student);
		return studentOptional;
	}

	@Override
	public void save(Student t) {
		if (t.getId() == null) {
			studentMapper.save(t);
		}else {
			studentMapper.edit(t);
		}
	}

	@Override
	public void deleteById(Integer id) {
		studentMapper.deleteById(id);
	}

	@Override
	public List<StudentResponse> findAllStudent() {
		List<Student> student = studentMapper.findAll();
		List<StudentResponse> studentResponses = student.stream().map(s -> toStudentResponse(s)).collect(Collectors.toList());
		return studentResponses;
	}
	
	public StudentResponse toStudentResponse(Student student) {
		StudentResponse studentResponse = new StudentResponse();
		studentResponse.setId(student.getId());
		studentResponse.setName(student.getName());
		studentResponse.setCode(student.getCode());
		studentResponse.setPhone(student.getPhone());
		studentResponse.setAddress(student.getAddress());
		Grade grade = gradeMapper.findById(student.getGradeId());
		studentResponse.setGrade(grade);
		return studentResponse;
	}

	@Override
	public void save(StudentDto studentDto) {
		Student student = new Student();
		student.setName(studentDto.getName());
		student.setCode(studentDto.getCode());
		student.setAddress(studentDto.getAddress());
		student.setGradeId(studentDto.getGradeId());
		student.setPhone(studentDto.getPhone());
		save(student);
	}

	@Override
	public List<Student> findStudentByGradeId(Integer id) {
		return studentMapper.findStudentByGradeId(id);
	}
}
