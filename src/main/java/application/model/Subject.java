package application.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Subject {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	@OneToMany(cascade= {CascadeType.ALL}) private List<SubjectClass> classes;
	@ElementCollection @Column(name="questions") private List<String> questions;
	
	protected Subject(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SubjectClass> getClasses() {
		return classes;
	}

	public void setClasses(List<SubjectClass> classes) {
		this.classes = classes;
	}

	public List<String> getQuestions() {
		return questions;
	}

	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}

	public Subject(Long id, String name, List<SubjectClass> classes, List<String> questions) {
		super();
		this.id = id;
		this.name = name;
		this.classes = classes;
		this.questions = questions;
	};
	
}
