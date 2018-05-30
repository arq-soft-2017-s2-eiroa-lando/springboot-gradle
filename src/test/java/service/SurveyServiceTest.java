package service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import application.Application;
import application.controller.dto.Answer;
import application.model.StudentSurvey;
import application.model.Survey;
import application.model.SurveyStatistics;
import application.persistence.SurveyRepository;
import application.service.StudentSurveyService;
import application.service.SurveyService;
import utils.SurveyFactory;

@ContextConfiguration(classes = Application.class)
@RunWith(SpringRunner.class)
public class SurveyServiceTest {
	
	@Autowired private SurveyService service;
    @MockBean private SurveyRepository surveyRepository;    
    @MockBean private StudentSurveyService studentSurveyService;
	
	@Test
	public void save() {
		Survey s = SurveyFactory.createSurvey();
		service.save(s);
		 
		verify(surveyRepository).save(s);
		verify(studentSurveyService).createStudentSurveys(any(Survey.class));
	}
	
	@Test
	public void saveAnswer() {
		List<Answer> answers = new ArrayList<Answer>();
		Answer a = new Answer(1,"Cursaria en C1");
		answers.add(a);
		StudentSurvey ss = new StudentSurvey(1l, "email", "period", "comment", SurveyFactory.createSurvey().getSubjects());
		when(studentSurveyService.findStudentSurvey(1)).thenReturn(ss);
		Survey s = SurveyFactory.createSurvey();
		when(surveyRepository.findOne(1l)).thenReturn(s);

		service.saveAnswer(1, answers);
		
		verify(studentSurveyService).findStudentSurvey(1);
		verify(studentSurveyService).save(any(StudentSurvey.class));
		verify(surveyRepository).save(any(Survey.class));
		assertTrue(ss.isCompleted());
		assertTrue(s.getCompletedSurveys() == 1);
	}
	
	@Test
	public void getAllSurveyStatistics() {
		List<Survey> surveys = new ArrayList<Survey>();
		Survey survey = SurveyFactory.createSurvey();
		surveys.add(survey);
		when(surveyRepository.findAll()).thenReturn(surveys);
		List<StudentSurvey> studentSurveys = new ArrayList<StudentSurvey>();
		studentSurveys.add(new StudentSurvey(1l, "email", "period", "comment", survey.getSubjects()));
  		when(studentSurveyService.findAllStudentSurveys(1l)).thenReturn(studentSurveys);
		
  		List<SurveyStatistics> statistics = service.getAllSurveyStatistics();

  		assertNotNull(statistics);
		verify(studentSurveyService).findAllStudentSurveys(1l);
		verify(surveyRepository).findAll();
	}
	

}
