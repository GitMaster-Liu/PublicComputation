package com.goku.coreui.sys.model;

import java.util.Date;

public class KnowledgeStatistic {
	private String knowledge;
	private Date questionCreateTime;
	private String studentCount;
	private String answerCount;
	private String correctCount;
	private String accuracy;

	public String getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}

	public Date getQuestionCreateTime() {
		return questionCreateTime;
	}

	public void setQuestionCreateTime(Date questionCreateTime) {
		this.questionCreateTime = questionCreateTime;
	}

	public String getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(String studentCount) {
		this.studentCount = studentCount;
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
