package application.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.model.Survey;
import application.persistence.SurveyRepository;

@Service
public class SurveyService {

	@Autowired private SurveyRepository surveyR;
	
	public Survey find() {
		Iterator<Survey> i = surveyR.findAll().iterator();
		if(i.hasNext()) {
			return i.next();
		}
		return null;
	}
	
	public void save(Survey s) {
		surveyR.save(s);
	}
}
