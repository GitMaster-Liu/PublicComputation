package com.goku.coreui.sys.mapper;

import java.util.List;

import com.goku.coreui.sys.model.KnowledgeStatistic;
import com.goku.coreui.sys.model.LessonKnowlgdge;

public interface LessonKnowlgdgeMapper {
	List<KnowledgeStatistic> getKnowledge(String lessonId);
	
	//分页专用
	List<KnowledgeStatistic> getKnowledgeForPage(String lessonId, int startRow, int limit);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lesson_knowledge
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lesson_knowledge
     *
     * @mbg.generated
     */
    int insert(LessonKnowlgdge record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lesson_knowledge
     *
     * @mbg.generated
     */
    int insertSelective(LessonKnowlgdge record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lesson_knowledge
     *
     * @mbg.generated
     */
    LessonKnowlgdge selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lesson_knowledge
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LessonKnowlgdge record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lesson_knowledge
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LessonKnowlgdge record);
	
}