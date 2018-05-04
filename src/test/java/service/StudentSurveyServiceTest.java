package service;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import application.Application;
import application.model.StudentSurvey;
import application.model.Survey;
import application.persistence.StudentSurveyRepository;
import application.service.StudentSurveyService;
import utils.SurveyFactory;

@ContextConfiguration(classes = Application.class)
@RunWith(SpringRunner.class)
public class StudentSurveyServiceTest {

	@Autowired private StudentSurveyService service;
	
	@MockBean StudentSurveyRepository repo;
	
	@Test
	public void findAllStudentSurveys() {
		service.findAllStudentSurveys(1l);
		verify(repo).findBySurveyID(1l);
	}
	
	@Test
	public void findStudentSurvey( ) {
		service.findStudentSurvey(1);
		verify(repo).findBySurveyHash(1);
	}
	
	@Test
	public void save() {
		StudentSurvey survey = new StudentSurvey(1l, "email", "period", "comment", null);
		service.save(survey);
		verify(repo).save(survey);
	}
	
	@Test
	public void createStudentSurveys() {
		Survey s = SurveyFactory.createSurvey();
		service.createStudentSurveys(s);
	}
}
