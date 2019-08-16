package com.goku.coreui.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goku.coreui.sys.mapper.LessonKnowlgdgeMapper;
import com.goku.coreui.sys.model.KnowledgeStatistic;
import com.goku.coreui.sys.service.LessonKnowledgeService;
@Service
public class LessonKnowledgeServiceImpl implements LessonKnowledgeService{
	@Autowired
	LessonKnowlgdgeMapper lessonKnowlgdgeMapper;
	@Override
	public List<KnowledgeStatistic> getKnowledge(String lessonId) {
		return lessonKnowlgdgeMapper.getKnowledge(lessonId);
	}
	@Override
	public List<KnowledgeStatistic> getKnowledgeForPage(String lessonId, int startRow, int limit) {
		return lessonKnowlgdgeMapper.getKnowledgeForPage(lessonId,startRow,limit);
	}
}
