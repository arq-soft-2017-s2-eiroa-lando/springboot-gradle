package application.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.controller.dto.Answer;
import application.model.StudentSurvey;
import application.model.Subject;
import application.model.Survey;
import application.model.SurveyStatistics;
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

	public Iterable<StudentSurvey> findAllStudentSurveys(Long surveyId) {
		return studentR.findBySurveyID(surveyId);
	}
	
	public void save(Survey s) {
		Survey persistedSurvey = surveyR.save(s);
		
		//Crea todas las encuestas para los alumnos
		List<StudentSurvey> surveys = new ArrayList<StudentSurvey>();
		for( String email : s.getEmails().split(",") ) {
			List<Subject> subjects = cloneSubjects(s.getSubjects());
			StudentSurvey ss = new StudentSurvey(persistedSurvey.getId(), email, s.getPeriod(), s.getComment(), subjects);
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

	public void saveAnswer(int surveyHash, List<Answer> answers) {
		StudentSurvey survey = findStudentSurvey(surveyHash);
		for(Answer a : answers) {
			Subject s = survey.getSubject(a.getId());
			s.setOptionChosen(a.getOption());
		}
		survey.setCompleted(true);
		studentR.save(survey);
		
		Survey s = surveyR.findOne(survey.getSurveyID());
		s.setIncrementCompletedSurveys();
		surveyR.save(s);
	}

	public List<SurveyStatistics> getAllSurveyStatistics() {
		List<SurveyStatistics> allStatistics = new LinkedList<SurveyStatistics>();
		
		Iterable<Survey> allSurveys = this.surveyR.findAll();
		Iterator<Survey> it = allSurveys.iterator();
		while(it.hasNext()) {
			Survey s = it.next();
			Iterable<StudentSurvey> surveys = findAllStudentSurveys(s.getId());
			SurveyStatistics stat = new SurveyStatistics(s, surveys);
			allStatistics.add(stat);
		}
		
		return allStatistics;
	}

	
}
