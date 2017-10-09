package application.model;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Student {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private int studentFileNumber;
	
	@OneToMany(cascade = CascadeType.MERGE)
	private Set<Subject> subjects;
	
	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}

	public int getStudentFileNumber() {
		return studentFileNumber;
	}

	public void setStudentFileNumber(int studentFileNumber) {
		this.studentFileNumber = studentFileNumber;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Student(String name, int studentFileNumber) {
		this.name = name;
		this.studentFileNumber = studentFileNumber;
		this.subjects = new TreeSet<Subject>();
	}

	public Student() {
	}
	
}
