package application.model;

import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class StudentSurvey {

	@Id @GeneratedValue(strategy=GenerationType.AUTO) private Long id;
	private int surveyHash;
	private String email;
	private String period;
	private String comment;
	@OneToMany(cascade= {CascadeType.ALL}) private List<Subject> subjects;
	
	public int getSurveyHash() {
		return surveyHash;
	}
	public void setSurveyHash(int surveyHash) {
		this.surveyHash = surveyHash;
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
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public StudentSurvey(String email, String period, String comment, List<Subject> subjects) {
		this.surveyHash = getSaltString().hashCode();
		this.email = email;
		this.period = period;
		this.comment = comment;
		this.subjects = subjects;
	}
	public StudentSurvey() {
	}
	
	private String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
	
	
}
