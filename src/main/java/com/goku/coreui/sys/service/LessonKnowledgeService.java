package com.goku.coreui.sys.service;

import java.util.List;

import com.goku.coreui.sys.model.KnowledgeStatistic;

public interface LessonKnowledgeService {
	List<KnowledgeStatistic> getKnowledge(String lessonId);
	
	//分页显示
	List<KnowledgeStatistic> getKnowledgeForPage(String lessonId, int startRow, int limit);
}
