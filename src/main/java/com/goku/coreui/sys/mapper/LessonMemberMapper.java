package com.goku.coreui.sys.mapper;

import java.util.List;

import com.goku.coreui.sys.model.LessonMember;
import com.goku.coreui.sys.model.StudentAccuracyStatistic;
import com.goku.coreui.sys.model.StudentKnowledgeStatistic;

public interface LessonMemberMapper {
	//获得该课程所有学生的答题情况
	List<StudentAccuracyStatistic> getMemberByLessonId(String lessonId);
	//获得该课程所有学生的答题情况(分页专用)
	List<StudentAccuracyStatistic> getMemberByLessonIdForPage(String lessonId,int startRow,int limit);
	//获得该学生的各个知识点的答题情况
	List<StudentKnowledgeStatistic> getKnowledgeOfStudentByLessonId(String lessonId,String memberId);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lesson_member
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lesson_member
     *
     * @mbg.generated
     */
    int insert(LessonMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lesson_member
     *
     * @mbg.generated
     */
    int insertSelective(LessonMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lesson_member
     *
     * @mbg.generated
     */
    LessonMember selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lesson_member
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LessonMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lesson_member
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LessonMember record);
}