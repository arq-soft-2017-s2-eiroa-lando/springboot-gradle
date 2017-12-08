package application.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Survey {

	@Id @GeneratedValue(strategy=GenerationType.AUTO) private Long id;
	private String period;
	private String comment;
	@OneToMany(cascade= {CascadeType.ALL}) private List<Subject> subjects;
	private String emails;
	private int completedSurveys;
	private int totalSurveys;
	
	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public int getTotalSurveys() {
		return totalSurveys;
	}

	public void setTotalSurveys(int totalSurveys) {
		this.totalSurveys = totalSurveys;
	}

	public int getCompletedSurveys() {
		return completedSurveys;
	}

	public void setCompletedSurveys(int completedSurveys) {
		this.completedSurveys = completedSurveys;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public Survey() {}

}
