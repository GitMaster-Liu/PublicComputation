package com.goku.coreui.sys.model;

import java.util.Date;

public class LessonKnowlgdge {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson_knowledge.ID
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson_knowledge.NO
     *
     * @mbg.generated
     */
    private Integer no;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson_knowledge.lessonID
     *
     * @mbg.generated
     */
    private String lessonid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson_knowledge.knowledge
     *
     * @mbg.generated
     */
    private String knowledge;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson_knowledge.createTime
     *
     * @mbg.generated
     */
    private Date createtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson_knowledge.ID
     *
     * @return the value of lesson_knowledge.ID
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson_knowledge.ID
     *
     * @param id the value for lesson_knowledge.ID
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson_knowledge.NO
     *
     * @return the value of lesson_knowledge.NO
     *
     * @mbg.generated
     */
    public Integer getNo() {
        return no;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson_knowledge.NO
     *
     * @param no the value for lesson_knowledge.NO
     *
     * @mbg.generated
     */
    public void setNo(Integer no) {
        this.no = no;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson_knowledge.lessonID
     *
     * @return the value of lesson_knowledge.lessonID
     *
     * @mbg.generated
     */
    public String getLessonid() {
        return lessonid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson_knowledge.lessonID
     *
     * @param lessonid the value for lesson_knowledge.lessonID
     *
     * @mbg.generated
     */
    public void setLessonid(String lessonid) {
        this.lessonid = lessonid == null ? null : lessonid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson_knowledge.knowledge
     *
     * @return the value of lesson_knowledge.knowledge
     *
     * @mbg.generated
     */
    public String getKnowledge() {
        return knowledge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson_knowledge.knowledge
     *
     * @param knowledge the value for lesson_knowledge.knowledge
     *
     * @mbg.generated
     */
    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge == null ? null : knowledge.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson_knowledge.createTime
     *
     * @return the value of lesson_knowledge.createTime
     *
     * @mbg.generated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson_knowledge.createTime
     *
     * @param createtime the value for lesson_knowledge.createTime
     *
     * @mbg.generated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}