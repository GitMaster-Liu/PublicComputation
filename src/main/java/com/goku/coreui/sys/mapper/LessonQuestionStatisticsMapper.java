package com.goku.coreui.sys.mapper;

import com.goku.coreui.sys.model.LessonQuestionStatistics;

public interface LessonQuestionStatisticsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lesson_question_statistics
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lesson_question_statistics
     *
     * @mbg.generated
     */
    int insert(LessonQuestionStatistics record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lesson_question_statistics
     *
     * @mbg.generated
     */
    int insertSelective(LessonQuestionStatistics record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lesson_question_statistics
     *
     * @mbg.generated
     */
    LessonQuestionStatistics selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lesson_question_statistics
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LessonQuestionStatistics record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lesson_question_statistics
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LessonQuestionStatistics record);
}