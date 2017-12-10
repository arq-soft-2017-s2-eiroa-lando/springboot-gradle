package application.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SurveyStatistics {
	
	private String period;
	private int totalSurveys;
	private int surveysCompleted;
	private List<StatisticClass> classes;
	
	public SurveyStatistics(Survey s, Iterable<StudentSurvey> answers) {
		this.period = s.getPeriod();
		this.totalSurveys = s.getTotalSurveys();
		this.surveysCompleted = s.getCompletedSurveys();
		
		Map<String, StatisticClass> map = new HashMap<String, StatisticClass>();
		
		//TODO probablemente un algoritmo ineficiente para recoletar las opciones elegidas.
		//Se puede tambien pensar en persistir las estadisticas y modificarlas por cada vez que un alumno responde
		//sin embargo se debe evaluar si hacer eso es mejor.
		for(Subject subject : s.getSubjects()) {
			for(SubjectClass c : subject.getClasses()) {
				map.put(subject.getName()+c.getName() , new StatisticClass(subject.getName(), c.getName(), c.getSchedules(), c.getSize()));
			}
		}
		
		Iterator<StudentSurvey> iterator = answers.iterator();
		while(iterator.hasNext()) {
			StudentSurvey answer = iterator.next();
			if(answer.isCompleted()) {
				for( Subject answerSubject : answer.getSubjects()) {
					String option = answerSubject.getOptionChosen();
					//TODO está manera de mapear opciones a comisiones es fea, y tampoco se contabilizan las otras opciones elegidas.
					if(option.contains("Cursaría en ")) {
						String className = option.substring(12, option.length());
						map.get(answerSubject.getName()+className).incEnrolled();
					}
				}
			}
		}
		
		this.classes = new ArrayList<StatisticClass>(map.values());
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public int getTotalSurveys() {
		return totalSurveys;
	}

	public void setTotalSurveys(int totalSurveys) {
		this.totalSurveys = totalSurveys;
	}

	public int getSurveysCompleted() {
		return surveysCompleted;
	}

	public void setSurveysCompleted(int surveysCompleted) {
		this.surveysCompleted = surveysCompleted;
	}

	public List<StatisticClass> getClasses() {
		return classes;
	}

	public void setClasses(List<StatisticClass> classes) {
		this.classes = classes;
	}

	public SurveyStatistics(String period, int totalSurveys, int surveysCompleted, List<StatisticClass> classes) {
		super();
		this.period = period;
		this.totalSurveys = totalSurveys;
		this.surveysCompleted = surveysCompleted;
		this.classes = classes;
	}

	public SurveyStatistics() {
		super();
	}

	
}
