package application.persistence;

import org.springframework.data.repository.CrudRepository;

import application.model.StudentSurvey;

public interface StudentSurveyRepository extends CrudRepository<StudentSurvey, Long>{

	StudentSurvey findBySurveyHash(int surveyHash);
	
	Iterable<StudentSurvey> findBySurveyID(Long surveyID);
	
}
