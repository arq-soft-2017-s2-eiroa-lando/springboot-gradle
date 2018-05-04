package utils;

import java.util.ArrayList;
import java.util.List;

import application.model.Subject;
import application.model.SubjectClass;
import application.model.Survey;

public class SurveyFactory {

	public static Survey createSurvey() {
		List<Subject> subjects = new ArrayList<Subject>();
		List<SubjectClass> classes = new ArrayList<SubjectClass>();
		List<String> schedules = new ArrayList<String>();
		schedules.add("Jueves 20-24hs");
	    SubjectClass sc = new SubjectClass("C1", "Cacho", 20, schedules);
	    classes.add(sc);
		List<String> options = new ArrayList<String>();
		options.add("Cursaria en C1");
		options.add("No voy a cursar");
		Subject subject = new Subject("Matematica 1", classes, options, "");
		subject.setId(1l);
	    subjects.add(subject);
		Survey s = new Survey("1er cuatrimestre 2018", "fecha de cierre 20/03", subjects, "email@email.com,email2@email.com");
		s.setId(1l);
		return s;
	}
	
}
