package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.model.Student;
import application.persistence.StudentRepository;

@Service
public class StudentService{
	
	@Autowired
	private StudentRepository studRepo;
	
	
	public Student getStudentByFileNumber(int fileNumber) {
		return studRepo.findByStudentFileNumber(fileNumber);
	}

	public void save(Student s) {
		studRepo.save(s);
	}

}
