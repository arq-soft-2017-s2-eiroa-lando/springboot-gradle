package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.controller.dto.Answer;
import application.service.SurveyService;

@RestController
@RequestMapping("api" + "/answer")
@CrossOrigin
public class AnswerController {

	@Autowired SurveyService service;
	
	@PutMapping(value="{surveyHash}" , consumes="application/json")
	public @ResponseBody void saveAnswer(@PathVariable("surveyHash") int surveyHash , @RequestBody List<Answer> answers) {
		service.saveAnswer(surveyHash, answers);
	}
	
}
