package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import application.Application;
import application.model.Subject;
import application.model.SubjectClass;
import application.model.Survey;
import application.persistence.SurveyRepository;

@ContextConfiguration(classes = Application.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class SurveyRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;
 
	@Autowired
	private SurveyRepository surveyRepository;
	
	@Test
	public void saveSurvey() {
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
	    subjects.add(subject);
		Survey survey = new Survey("1er cuatrimestre 2018", "fecha de cierre 20/03", subjects, "email@email.com,email2@email.com");
	    entityManager.persist(survey);
	    entityManager.flush();
	 
	    List<Survey> found = Lists.newArrayList(surveyRepository.findAll());
	    assertTrue(found.size() == 1);
	    assertEquals(survey, found.get(0));
	}
	
}
