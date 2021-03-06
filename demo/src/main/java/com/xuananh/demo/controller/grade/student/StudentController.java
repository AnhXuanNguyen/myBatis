package com.xuananh.demo.controller.grade.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xuananh.demo.dto.StudentDto;
import com.xuananh.demo.dto.StudentResponse;
import com.xuananh.demo.model.Student;
import com.xuananh.demo.service.grade.IGradeService;
import com.xuananh.demo.service.student.IStudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private IStudentService studentService;
	@Autowired
	private IGradeService gradeService;
	
	@GetMapping
	public ModelAndView findAll() {
		ModelAndView modelAndView = new ModelAndView("student/students");
		List<StudentResponse> students = studentService.findAllStudent();
		modelAndView.addObject("students", students);
		return modelAndView;
	}
	
	@GetMapping("/create")
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView("student/create");
		modelAndView.addObject("student", new StudentDto());
		modelAndView.addObject("grades", gradeService.findAll());
		return modelAndView;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@ModelAttribute StudentDto studentDto) {
		studentService.save(studentDto);
		ModelAndView modelAndView = new ModelAndView("student/students");
		List<StudentResponse> students = studentService.findAllStudent();
		modelAndView.addObject("students", students);
		return modelAndView;
	}
	
	@GetMapping("/remove/{id}")
	public ModelAndView remove(@PathVariable Integer id) {
		studentService.deleteById(id);
		ModelAndView modelAndView = new ModelAndView("student/students");
		List<StudentResponse> students = studentService.findAllStudent();
		modelAndView.addObject("students", students);
		return modelAndView;
	}
	
	@GetMapping("edit/{id}")
	public ModelAndView edit(@PathVariable Integer id) {
		Optional<Student> studentOptional = studentService.findById(id);
		if (!studentOptional.isPresent()) {
			return new ModelAndView("grade/404");
		}
		ModelAndView modelAndView = new ModelAndView("student/edit");
		modelAndView.addObject("student", studentOptional.get());
		modelAndView.addObject("grades", gradeService.findAll());
		return modelAndView;
	}
	
	@PostMapping("/save-edit")
	public ModelAndView saveEdit(@ModelAttribute Student student) {
		studentService.save(student);
		ModelAndView modelAndView = new ModelAndView("student/students");
		List<StudentResponse> students = studentService.findAllStudent();
		modelAndView.addObject("students", students);
		return modelAndView;
	}
}
