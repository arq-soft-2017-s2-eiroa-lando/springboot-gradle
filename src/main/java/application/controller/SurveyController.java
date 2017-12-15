package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.controller.dto.Answer;
import application.model.StudentSurvey;
import application.model.Survey;
import application.model.SurveyStatistics;
import application.service.SurveyService;

@RestController
@RequestMapping("api" + "/survey")
@CrossOrigin
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
    	 
	@PostMapping(value="answer/{surveyHash}" , consumes="application/json")
	public @ResponseBody void saveAnswer(@PathVariable("surveyHash") int surveyHash , @RequestBody List<Answer> answers) {
		service.saveAnswer(surveyHash, answers);
	}
	
	@GetMapping(value="statistics", produces="application/json")
	public SurveyStatistics getStatistics() {
		Survey s = this.getCurrentSurvey();
		if(s == null) return null;
		Iterable<StudentSurvey> answers = this.getStudentSurveys();
		
		return new SurveyStatistics(s, answers);
	}
}
