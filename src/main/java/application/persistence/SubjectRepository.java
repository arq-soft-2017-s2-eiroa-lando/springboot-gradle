package application.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import application.model.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long> {

	List<Subject> findByName(String name);
	
}
