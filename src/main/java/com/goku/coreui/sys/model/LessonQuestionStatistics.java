package com.goku.coreui.sys.model;

import java.util.Date;

public class LessonQuestionStatistics {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson_question_statistics.ID
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson_question_statistics.questionID
     *
     * @mbg.generated
     */
    private String questionid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson_question_statistics.memberID
     *
     * @mbg.generated
     */
    private String memberid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson_question_statistics.answer
     *
     * @mbg.generated
     */
    private String answer;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson_question_statistics.answerTime
     *
     * @mbg.generated
     */
    private Date answertime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson_question_statistics.correct
     *
     * @mbg.generated
     */
    private Byte correct;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson_question_statistics.ID
     *
     * @return the value of lesson_question_statistics.ID
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson_question_statistics.ID
     *
     * @param id the value for lesson_question_statistics.ID
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson_question_statistics.questionID
     *
     * @return the value of lesson_question_statistics.questionID
     *
     * @mbg.generated
     */
    public String getQuestionid() {
        return questionid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson_question_statistics.questionID
     *
     * @param questionid the value for lesson_question_statistics.questionID
     *
     * @mbg.generated
     */
    public void setQuestionid(String questionid) {
        this.questionid = questionid == null ? null : questionid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson_question_statistics.memberID
     *
     * @return the value of lesson_question_statistics.memberID
     *
     * @mbg.generated
     */
    public String getMemberid() {
        return memberid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson_question_statistics.memberID
     *
     * @param memberid the value for lesson_question_statistics.memberID
     *
     * @mbg.generated
     */
    public void setMemberid(String memberid) {
        this.memberid = memberid == null ? null : memberid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson_question_statistics.answer
     *
     * @return the value of lesson_question_statistics.answer
     *
     * @mbg.generated
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson_question_statistics.answer
     *
     * @param answer the value for lesson_question_statistics.answer
     *
     * @mbg.generated
     */
    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson_question_statistics.answerTime
     *
     * @return the value of lesson_question_statistics.answerTime
     *
     * @mbg.generated
     */
    public Date getAnswertime() {
        return answertime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson_question_statistics.answerTime
     *
     * @param answertime the value for lesson_question_statistics.answerTime
     *
     * @mbg.generated
     */
    public void setAnswertime(Date answertime) {
        this.answertime = answertime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson_question_statistics.correct
     *
     * @return the value of lesson_question_statistics.correct
     *
     * @mbg.generated
     */
    public Byte getCorrect() {
        return correct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson_question_statistics.correct
     *
     * @param correct the value for lesson_question_statistics.correct
     *
     * @mbg.generated
     */
    public void setCorrect(Byte correct) {
        this.correct = correct;
    }
}