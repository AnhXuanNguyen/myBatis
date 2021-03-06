package com.xuananh.demo.controller.grade;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xuananh.demo.dto.GradeDto;
import com.xuananh.demo.model.Grade;
import com.xuananh.demo.service.grade.IGradeService;

@Controller
@RequestMapping("/grade")
public class GradeController {
	@Autowired
	private IGradeService gradeService;
	
	@GetMapping
	public ModelAndView findAll() {
		ModelAndView modelAndView = new ModelAndView("grade/grades");
		modelAndView.addObject("grades", gradeService.findAll());
		return modelAndView;
	}
	
	@GetMapping("/create")
	public ModelAndView showFormCreate() {
		ModelAndView modelAndView = new ModelAndView("grade/create");
		GradeDto gradeDto = new GradeDto();
		modelAndView.addObject("grade", gradeDto);
		return modelAndView;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@ModelAttribute GradeDto gradeDto) {
		gradeService.save(gradeDto);
		ModelAndView modelAndView = new ModelAndView("grade/grades");
		modelAndView.addObject("grades", gradeService.findAll());
		return modelAndView;
	}
	
	@GetMapping("/remove/{id}")
	public ModelAndView deleteById(@PathVariable Integer id) {
		gradeService.deleteById(id);
		ModelAndView modelAndView = new ModelAndView("grade/grades");
		modelAndView.addObject("grades", gradeService.findAll());
		return modelAndView;
	}
	@GetMapping("/edit/{id}")
	public ModelAndView editById(@PathVariable Integer id) {
		Optional<Grade> gradeOptinal = gradeService.findById(id);
		if(!gradeOptinal.isPresent()) {
			return new ModelAndView("grade/404");
		}
		ModelAndView modelAndView = new ModelAndView("grade/edit");
		modelAndView.addObject("grade", gradeOptinal.get());
		return modelAndView;
	}
	@PostMapping("/save-edit")
	public ModelAndView saveEdit(@ModelAttribute Grade grade) {
		gradeService.save(grade);
		ModelAndView modelAndView = new ModelAndView("grade/grades");
		modelAndView.addObject("grades", gradeService.findAll());
		return modelAndView;
	}
}
