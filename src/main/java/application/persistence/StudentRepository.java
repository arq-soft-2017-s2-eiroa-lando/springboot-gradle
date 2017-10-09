package application.persistence;

import org.springframework.data.repository.CrudRepository;

import application.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

	Student findByName(String name);

	Student findByStudentFileNumber(Integer fileNumber);
	
}
