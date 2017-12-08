package application.persistence;

import org.springframework.data.repository.CrudRepository;

import application.model.Survey;

public interface SurveyRepository extends CrudRepository<Survey, Long> {

	
}
