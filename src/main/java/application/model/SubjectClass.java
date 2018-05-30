package application.model;

import java.util.ArrayList;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String teachers;
    private int size;
    @ElementCollection
    @Column(name = "schedule")
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

    public SubjectClass() {
    }

    public SubjectClass(String name, String teachers, int size, List<String> schedules) {
        this.name = name;
        this.teachers = teachers;
        this.size = size;
        this.schedules = schedules;
    }

    public SubjectClass cloneClass() {
        return new SubjectClass(name, teachers, size, cloneSchedules());
    }

    private List<String> cloneSchedules() {
        List<String> list = new ArrayList<String>();
        list.addAll(schedules);
        return list;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SubjectClass))
            return false;
        SubjectClass s = (SubjectClass) obj;

        return this.id == s.id && this.name.equals(s.name) && this.teachers.equals(s.teachers) && this.size == s.size
                && this.schedules.equals(s.schedules);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" =====================" + "\n");
        sb.append("   - Class name: " + this.name + "\n");
        
        return sb.toString();
    }

}
