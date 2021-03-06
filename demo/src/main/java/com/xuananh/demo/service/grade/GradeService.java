package com.xuananh.demo.service.grade;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuananh.demo.dto.GradeDto;
import com.xuananh.demo.model.Grade;
import com.xuananh.demo.model.Student;
import com.xuananh.demo.repository.GradeMapper;
import com.xuananh.demo.repository.StudentMapper;
@Service
public class GradeService implements IGradeService {
	
	@Autowired
	private GradeMapper gradeMapper;
	@Autowired
	private StudentMapper studentMapper;

	@Override
	public List<Grade> findAll() {
		// TODO Auto-generated method stub
		return gradeMapper.findAll();
	}

	@Override
	public Optional<Grade> findById(Integer id) {
		Grade grade = gradeMapper.findById(id);
		Optional<Grade> gradeOptional = Optional.ofNullable(grade);
		return gradeOptional;
	}

	@Override
	public void save(Grade t) {
		if (t.getId() == null) {
			gradeMapper.save(t);
		}else {
			gradeMapper.edit(t);
		}
	}


	@Override
	public void deleteById(Integer id) {
		List<Student> students = studentMapper.findStudentByGradeId(id);
		students.stream().forEach(s -> changeGrade(s));
		gradeMapper.deleteById(id);
	}

	private void changeGrade(Student student) {
		student.setGradeId(null);
		studentMapper.edit(student);
	}

	@Override
	public void save(GradeDto gradeDto) {
		Grade grade = new Grade();
		grade.setName(gradeDto.getName());
		save(grade);
	}	

}
