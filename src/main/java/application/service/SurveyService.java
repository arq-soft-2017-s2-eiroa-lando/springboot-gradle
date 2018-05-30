package application.service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(SurveyService.class); 
	
	public void save(Survey s) {
	    log.info("Saving survey for period: " + s.getPeriod());
		Survey persistedSurvey = surveyR.save(s);
		studentSurveyService.createStudentSurveys(persistedSurvey);
	}
	

	public void saveAnswer(int surveyHash, List<Answer> answers) {
	    log.info("Saving answers for hash " + surveyHash); 
		boolean isEdit = false;
	    StudentSurvey survey = studentSurveyService.findStudentSurvey(surveyHash);
		if(survey.isCompleted()) {
		    isEdit = true;
		}
		for(Answer a : answers) {
			Subject s = survey.getSubject(a.getId());
			s.setOptionChosen(a.getOption());
		}
		survey.setCompleted(true);
		studentSurveyService.save(survey);
		
		if(!isEdit) { 
		    Survey s = surveyR.findOne(survey.getSurveyID());
		    s.setIncrementCompletedSurveys(); 
		    surveyR.save(s);
		}
	}

	public List<SurveyStatistics> getAllSurveyStatistics() {
	    log.info("Getting survey statistics");
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
