package application.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.model.StudentSurvey;
import application.model.Subject;
import application.model.Survey;
import application.persistence.StudentSurveyRepository;
import application.persistence.SurveyRepository;

@Service
public class SurveyService {

	@Autowired private SurveyRepository surveyR;
	@Autowired private StudentSurveyRepository studentR;
	
	public Survey find() {
		Iterator<Survey> i = surveyR.findAll().iterator();
		if(i.hasNext()) {
			return i.next();
		}
		return null;
	}

	public Iterable<StudentSurvey> findAllStudentSurveys(){
		return studentR.findAll();
	}
	
	public void save(Survey s) {
		surveyR.save(s);
		
		//Crear todas las encuestas para los alumnos
		List<StudentSurvey> surveys = new ArrayList<StudentSurvey>();
		for( String email : s.getEmails().split(",") ) {
			List<Subject> subjects = cloneSubjects(s.getSubjects());
			StudentSurvey ss = new StudentSurvey(email, s.getPeriod(), s.getComment(), subjects);
			surveys.add(ss);
		}
		studentR.save(surveys);
				
	}

	/**
	 * Clone the list of entities so we persist an instance for each student
	 */
	private List<Subject> cloneSubjects(List<Subject> subjects) {
		List<Subject> ss = new ArrayList<Subject>();
		
		for(Subject s: subjects) {
			ss.add(s.cloneSubject());
		}
		return ss;
	}

	public StudentSurvey findStudentSurvey(int surveyHash) {
		return studentR.findBySurveyHash(surveyHash);
	}
}
