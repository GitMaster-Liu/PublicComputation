package com.goku.coreui.sys.model;

import java.util.Date;

public class LessonQuestion {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson_question.ID
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson_question.knowledgeID
     *
     * @mbg.generated
     */
    private String knowledgeid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson_question.answer
     *
     * @mbg.generated
     */
    private String answer;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson_question.createTime
     *
     * @mbg.generated
     */
    private Date createtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson_question.state
     *
     * @mbg.generated
     */
    private Byte state;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson_question.studentCount
     *
     * @mbg.generated
     */
    private Integer studentcount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson_question.answerCount
     *
     * @mbg.generated
     */
    private Integer answercount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson_question.correctCount
     *
     * @mbg.generated
     */
    private Integer correctcount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson_question.accuracy
     *
     * @mbg.generated
     */
    private Byte accuracy;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson_question.ID
     *
     * @return the value of lesson_question.ID
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson_question.ID
     *
     * @param id the value for lesson_question.ID
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson_question.knowledgeID
     *
     * @return the value of lesson_question.knowledgeID
     *
     * @mbg.generated
     */
    public String getKnowledgeid() {
        return knowledgeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson_question.knowledgeID
     *
     * @param knowledgeid the value for lesson_question.knowledgeID
     *
     * @mbg.generated
     */
    public void setKnowledgeid(String knowledgeid) {
        this.knowledgeid = knowledgeid == null ? null : knowledgeid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson_question.answer
     *
     * @return the value of lesson_question.answer
     *
     * @mbg.generated
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson_question.answer
     *
     * @param answer the value for lesson_question.answer
     *
     * @mbg.generated
     */
    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson_question.createTime
     *
     * @return the value of lesson_question.createTime
     *
     * @mbg.generated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson_question.createTime
     *
     * @param createtime the value for lesson_question.createTime
     *
     * @mbg.generated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson_question.state
     *
     * @return the value of lesson_question.state
     *
     * @mbg.generated
     */
    public Byte getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson_question.state
     *
     * @param state the value for lesson_question.state
     *
     * @mbg.generated
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson_question.studentCount
     *
     * @return the value of lesson_question.studentCount
     *
     * @mbg.generated
     */
    public Integer getStudentcount() {
        return studentcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson_question.studentCount
     *
     * @param studentcount the value for lesson_question.studentCount
     *
     * @mbg.generated
     */
    public void setStudentcount(Integer studentcount) {
        this.studentcount = studentcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson_question.answerCount
     *
     * @return the value of lesson_question.answerCount
     *
     * @mbg.generated
     */
    public Integer getAnswercount() {
        return answercount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson_question.answerCount
     *
     * @param answercount the value for lesson_question.answerCount
     *
     * @mbg.generated
     */
    public void setAnswercount(Integer answercount) {
        this.answercount = answercount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson_question.correctCount
     *
     * @return the value of lesson_question.correctCount
     *
     * @mbg.generated
     */
    public Integer getCorrectcount() {
        return correctcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson_question.correctCount
     *
     * @param correctcount the value for lesson_question.correctCount
     *
     * @mbg.generated
     */
    public void setCorrectcount(Integer correctcount) {
        this.correctcount = correctcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson_question.accuracy
     *
     * @return the value of lesson_question.accuracy
     *
     * @mbg.generated
     */
    public Byte getAccuracy() {
        return accuracy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson_question.accuracy
     *
     * @param accuracy the value for lesson_question.accuracy
     *
     * @mbg.generated
     */
    public void setAccuracy(Byte accuracy) {
        this.accuracy = accuracy;
    }
}