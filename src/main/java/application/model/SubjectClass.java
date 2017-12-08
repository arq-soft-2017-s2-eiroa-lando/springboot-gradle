package application.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SubjectClass {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String teachers;
	private int size;	
	@ElementCollection
	@Column(name="schedule")
	private List<String> schedules;
	
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

	public String getTeachers() {
		return teachers;
	}

	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	
	public List<String> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<String> schedules) {
		this.schedules = schedules;
	}

	public SubjectClass() {}

	public SubjectClass(Long id, String name, String teachers, int size, List<String> schedules) {
		super();
		this.id = id;
		this.name = name;
		this.teachers = teachers;
		this.size = size;
		this.schedules = schedules;
	}
	
}
