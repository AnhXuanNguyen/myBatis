package com.xuananh.demo.service.grade;

import com.xuananh.demo.dto.GradeDto;
import com.xuananh.demo.model.Grade;
import com.xuananh.demo.service.IGeneralService;

public interface IGradeService extends IGeneralService<Grade> {
	public void save(GradeDto t);
}
