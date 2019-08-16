package com.goku.coreui.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goku.coreui.sys.mapper.LessonMapper;
import com.goku.coreui.sys.model.Lesson;
import com.goku.coreui.sys.model.User;
import com.goku.coreui.sys.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService {
	@Autowired
	LessonMapper lessonMapper;

	@Override
	public List<Lesson> getLessonById(String creatorID) {
		return lessonMapper.getLessonById(creatorID);
	}

	@Override
	public List<User> getMemberByLessonId(String lessonId) {
		return lessonMapper.getMemberByLessonId(lessonId);
	}

	@Override
	public Lesson getLesson(String lessonId) {
		return lessonMapper.getLesson(lessonId);
	}

}
