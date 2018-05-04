package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.model.StudentSurvey;
import application.service.StudentSurveyService;

@RestController
@RequestMapping("api" + "/student-survey")
@CrossOrigin
public class StudentSurveyController {

	@Autowired StudentSurveyService service;
	 
	@GetMapping(value="/all/{surveyID}" , produces="application/json")
	public @ResponseBody Iterable<StudentSurvey> getStudentSurveys(@PathVariable("surveyID") long surveyID) {
	    return service.findAllStudentSurveys(surveyID);
	}
	
	@GetMapping(value="{surveyHash}" , produces="application/json")
	public @ResponseBody StudentSurvey getStudentSurvey(@PathVariable("surveyHash") int surveyHash) {
	    return service.findStudentSurvey(surveyHash);
	}
	
}
