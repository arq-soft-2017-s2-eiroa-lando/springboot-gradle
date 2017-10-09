package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.model.Student;
import application.model.Subject;
import application.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studService;

	@GetMapping(value = "/{fileNumber}")
	public Student getStudentByFileNumber(@PathVariable Integer fileNumber) {
		return studService.getStudentByFileNumber(1);
	}

	@PostMapping(value = "/subscribe/{fileNumber}", consumes = "application/json")
	public void subscribe( @RequestBody Subject subject, @PathVariable Integer fileNumber){
		Student st = studService.getStudentByFileNumber(fileNumber);
		st.getSubjects().stream().filter(s -> s.getName().equals(subject.getName())).findFirst().get().setSubscribed(true);
		studService.save(st);
	}

	@PostMapping(value = "/unsubscribe/{fileNumber}", consumes = "application/json")
	public void unsubscribe( @RequestBody Subject subject, @PathVariable Integer fileNumber){
		Student st = studService.getStudentByFileNumber(fileNumber);
		st.getSubjects().stream().filter(s -> s.getName().equals(subject.getName())).findFirst().get().setSubscribed(false);
		studService.save(st);
	}
}