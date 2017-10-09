package application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subject implements Comparable<Subject>{

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private boolean approved;
	
	private boolean subscribed;

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public boolean isSubscribed() {
		return subscribed;
	}

	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Subject(String name, boolean approved, boolean subscripted) {
		this.name = name;
		this.approved = approved;
		this.subscribed = subscripted;
	}

	protected Subject(){}

	@Override
	public int compareTo(Subject s) {
		return s.name.compareTo(this.name);
	}

	
	
	
}
