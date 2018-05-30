package application.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@OneToMany(fetch = FetchType.EAGER, cascade= {CascadeType.ALL}) private List<SubjectClass> classes;
	@ElementCollection @Column(name="options") private List<String> options;
	private String optionChosen;
	
	public String getOptionChosen() {
		return optionChosen;
	}

	public void setOptionChosen(String optionChosen) {
		this.optionChosen = optionChosen;
	}

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

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public Subject(String name, List<SubjectClass> classes, List<String> options, String optionChosen) {
		this.name = name;
		this.classes = classes;
		this.options = options;
		this.optionChosen = optionChosen;
	}

	public Subject cloneSubject() {
		return new Subject(name, cloneClasses(), cloneOptions(), optionChosen);
	}

	private List<String> cloneOptions() {
		List<String> list = new ArrayList<String>();
		list.addAll(options);
		return list;
	}

	private List<SubjectClass> cloneClasses() {
		List<SubjectClass> list = new ArrayList<SubjectClass>();
		for(SubjectClass sc : classes) {
			list.add(sc.cloneClass());
		}
		return list;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Subject)) return false;
		Subject s = (Subject) obj;
		return this.id == s.id && this.name.equals(s.name) && this.classes.equals(s.classes) 
				&& this.options.equals(s.options) && this.optionChosen.equals(s.optionChosen);
	}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" =====================" + "\n");
        sb.append(" - Name: " + this.name + "\n");
        sb.append(" - Classes: " + this.classes.toString() + "\n");
        
        return sb.toString();
    }
	
	
	
}
