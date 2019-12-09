package com.training.assessment;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "registeredtable")
public class AssessmentTable {
	@Id
	private int ID;
	
	@NotNull
	private int userId;
	
	@NotNull
	private String skillTopic;
	
	@NotNull
	private LocalDate scheduled_date;
	
	@NotNull
	private String assessment_type;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getSkillTopic() {
		return skillTopic;
	}
	public void setSkillTopic(String skillTopic) {
		this.skillTopic = skillTopic;
	}
	public LocalDate getScheduled_date() {
		return scheduled_date;
	}
	public void setScheduled_date(LocalDate scheduled_date) {
		this.scheduled_date = scheduled_date;
	}
	public String getAssessment_type() {
		return assessment_type;
	}
	public void setAssessment_type(String assessment_type) {
		this.assessment_type = assessment_type;
	}
	public AssessmentTable(int userId, String skillTopic, LocalDate scheduled_date, String assessment_type) {
		super();
		this.userId = userId;
		this.skillTopic = skillTopic;
		this.scheduled_date = scheduled_date;
		this.assessment_type = assessment_type;
		this.ID = this.hashCode();
	}
	public AssessmentTable() {
		super();
		this.ID = this.hashCode();
		// TODO Auto-generated constructor stub
	}
	

}
