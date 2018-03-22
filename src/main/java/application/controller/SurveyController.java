package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.model.Survey;
import application.service.SurveyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api" + "/survey")
@CrossOrigin
@Api(value="Surveys")
public class SurveyController {

	@Autowired SurveyService service;
	 
	@PostMapping(consumes="application/json")
	@ApiOperation(value = "Creates a new survey")
	public @ResponseBody void newSurvey(@RequestBody Survey s) {
		s.setTotalSurveys(s.getEmails().split(",").length);
		service.save(s);
	}
    	 
}
