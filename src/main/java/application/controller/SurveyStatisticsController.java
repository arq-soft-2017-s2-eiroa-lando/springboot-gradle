package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.model.SurveyStatistics;
import application.service.SurveyService;

@RestController
@RequestMapping("api" + "/statistics")
@CrossOrigin
public class SurveyStatisticsController {

	@Autowired SurveyService service;
	
	@GetMapping(produces="application/json")
	public List<SurveyStatistics> getStatistics() {
		return service.getAllSurveyStatistics();
	}
	
}
