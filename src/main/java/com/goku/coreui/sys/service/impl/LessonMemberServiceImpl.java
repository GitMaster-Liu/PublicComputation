package com.goku.coreui.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goku.coreui.sys.mapper.LessonMemberMapper;
import com.goku.coreui.sys.model.StudentAccuracyStatistic;
import com.goku.coreui.sys.model.StudentKnowledgeStatistic;
import com.goku.coreui.sys.service.LessonMemberService;
@Service
public class LessonMemberServiceImpl implements LessonMemberService{
	@Autowired
	LessonMemberMapper lessonMemberMapper;


	
	
	public List<StudentAccuracyStatistic> getMemberByLessonId(String lessonId){
		return lessonMemberMapper.getMemberByLessonId(lessonId);
	}
	//分页
	@Override
	public List<StudentAccuracyStatistic> getMemberByLessonId(String lessonId,int startRow, int limit){
		return lessonMemberMapper.getMemberByLessonIdForPage(lessonId,startRow,limit);
	}
	
	
	@Override
	public List<StudentKnowledgeStatistic> getKnowledgeOfStudentByLessonId(String lessonId, String memberId) {
		return lessonMemberMapper.getKnowledgeOfStudentByLessonId(lessonId, memberId);
	}
}
