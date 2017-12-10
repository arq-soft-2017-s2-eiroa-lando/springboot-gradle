package application.model;

import java.util.List;

/** Representa las estadisticas de una comision */
public class StatisticClass {

	private String subject;
	private String name;
	private int enrolled;
	private List<String> schedule;
	private int size;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEnrolled() {
		return enrolled;
	}

	public void setEnrolled(int enrolled) {
		this.enrolled = enrolled;
	}

	public List<String> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<String> schedule) {
		this.schedule = schedule;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public StatisticClass(String subject, String name, List<String> schedule, int size) {
		super();
		this.subject = subject;
		this.name = name;
		this.schedule = schedule;
		this.size = size;
	}

	public void incEnrolled() {
		this.enrolled ++ ;
	}

}
