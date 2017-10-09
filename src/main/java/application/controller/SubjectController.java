package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.model.Subject;
import application.persistence.SubjectRepository;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

	@Autowired
	private SubjectRepository repo;
	
    @RequestMapping(produces="application/json")
    public @ResponseBody Iterable<Subject> findAll() {
    	return repo.findAll();
    }
    

}