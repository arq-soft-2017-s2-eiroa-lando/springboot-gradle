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
import utils.SurveyFactory;

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
		Survey survey = SurveyFactory.createSurvey();
	    entityManager.persist(survey);
	    entityManager.flush();
	 
	    List<Survey> found = Lists.newArrayList(surveyRepository.findAll());
	    assertTrue(found.size() == 1);
	    assertEquals(survey, found.get(0));
	}
	
}
