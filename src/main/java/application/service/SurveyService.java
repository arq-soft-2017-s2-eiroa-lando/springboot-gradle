package application.service;

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
import application.persistence.SurveyRepository;

@Service
public class SurveyService {

	@Autowired private SurveyRepository surveyR;
	@Autowired private StudentSurveyService studentSurveyService;
	
	public Survey find() {
		Iterator<Survey> i = surveyR.findAll().iterator();
		if(i.hasNext()) {
			return i.next();
		}
		return null;
	}

	public void save(Survey s) {
		Survey persistedSurvey = surveyR.save(s);
		studentSurveyService.createStudentSurveys(persistedSurvey);
	}
	

	public void saveAnswer(int surveyHash, List<Answer> answers) {
		StudentSurvey survey = studentSurveyService.findStudentSurvey(surveyHash);
		for(Answer a : answers) {
			Subject s = survey.getSubject(a.getId());
			s.setOptionChosen(a.getOption());
		}
		survey.setCompleted(true);
		studentSurveyService.save(survey);
		
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
			Iterable<StudentSurvey> surveys = studentSurveyService.findAllStudentSurveys(s.getId());
			SurveyStatistics stat = new SurveyStatistics(s, surveys);
			allStatistics.add(stat);
		}
		
		return allStatistics;
	}

	
}
