package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.model.StudentSurvey;
import application.model.Survey;
import application.service.SurveyService;

@RestController
@RequestMapping("api" + "/survey")
public class SurveyController {

	@Autowired SurveyService service;
	
	@GetMapping(value="survey" , produces="application/json")
	public @ResponseBody Survey getCurrentSurvey() {
	    return service.find();
	}
	
	@GetMapping(value="student-surveys" , produces="application/json")
	public @ResponseBody Iterable<StudentSurvey> getStudentSurveys() {
	    return service.findAllStudentSurveys();
	}
	
	@GetMapping(value="student-survey/{surveyHash}" , produces="application/json")
	public @ResponseBody StudentSurvey getStudentSurvey(@PathVariable("surveyHash") int surveyHash) {
	    return service.findStudentSurvey(surveyHash);
	}
	
	 
	@PostMapping(value="new-survey" , consumes="application/json")
	public @ResponseBody void newSurvey(@RequestBody Survey s) {
		s.setTotalSurveys(s.getEmails().split(",").length);
		service.save(s);
	}
    	 
	
}
