package com.goku.coreui.sys.service;

import java.util.List;

import com.goku.coreui.sys.model.StudentAccuracyStatistic;
import com.goku.coreui.sys.model.StudentKnowledgeStatistic;

public interface LessonMemberService {
	//获得该课程所有学生的答题情况
	List<StudentAccuracyStatistic> getMemberByLessonId(String lessonId);
	
	//获得该课程所有学生的答题情况(分页)
	List<StudentAccuracyStatistic> getMemberByLessonId(String lessonId, int startRow, int limit);

	// 获得该学生的各个知识点的答题情况
	List<StudentKnowledgeStatistic> getKnowledgeOfStudentByLessonId(String lessonId, String memberId);
}
