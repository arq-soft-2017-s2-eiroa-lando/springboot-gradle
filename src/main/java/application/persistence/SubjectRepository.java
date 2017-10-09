package application.persistence;

import org.springframework.data.repository.CrudRepository;

import application.model.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long> {

	Subject findByName(String name);
	
}
