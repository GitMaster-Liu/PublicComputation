package com.goku.coreui.sys.service;

import java.util.List;

import com.goku.coreui.sys.model.Lesson;
import com.goku.coreui.sys.model.User;

public interface LessonService {
	public List<Lesson> getLessonById(String creatorID);

	public List<User> getMemberByLessonId(String lessonId);
	
	public Lesson getLesson(String lessonId);
}
