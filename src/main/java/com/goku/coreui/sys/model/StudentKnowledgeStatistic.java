package com.goku.coreui.sys.model;

import java.util.Date;

public class StudentKnowledgeStatistic {
	private String memberID;
	private String knowledge;
	private Date createTime;
	private String questionCount;
	private String answerCount;
	private String correctCount;
	private String accuracy;
	
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getQuestionCount() {
		return questionCount;
	}
	public void setQuestionCount(String questionCount) {
		this.questionCount = questionCount;
	}
	public String getAnswerCount() {
		return answerCount;
	}
	public void setAnswerCount(String answerCount) {
		this.answerCount = answerCount;
	}
	public String getCorrectCount() {
		return correctCount;
	}
	public void setCorrectCount(String correctCount) {
		this.correctCount = correctCount;
	}
	public String getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}
	
}
