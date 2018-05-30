package integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import application.Application;
import application.controller.dto.Answer;
import application.model.StatisticClass;
import application.model.StudentSurvey;
import application.model.Subject;
import application.model.SubjectClass;
import application.model.Survey;
import application.model.SurveyStatistics;
import application.persistence.StudentSurveyRepository;
import application.service.SurveyService;

/**
 * Validate a large flow in the survey context
 *
 */
@ContextConfiguration(classes = Application.class)
@RunWith(SpringRunner.class)
public class SurveyFlowTest {

    @Autowired private SurveyService service;
    @Autowired private StudentSurveyRepository studentR;
    
    @Test
    public void surveyWithNoAnswers() {
        
        //Hice un modelo de mierda, get over it.
        
        //Create a survey
        Survey survey = createSurvey();
        service.save(survey);

        SurveyStatistics stats = service.getAllSurveyStatistics().get(0);        
        
        assertEquals(survey.getPeriod() , stats.getPeriod());
        assertEquals(0, stats.getSurveysCompleted());
        assertEquals(20, stats.getClasses().size());
        
        
        //See empty statistics
        for(StatisticClass c : stats.getClasses()) {
            assertEquals(0, c.getEnrolled());
        }
        
        List<Integer> surveyHashes = new ArrayList<Integer>();
        Iterable<StudentSurvey> allStudentSurveys = studentR.findAll();
        Iterator<StudentSurvey> studentSurveys = allStudentSurveys.iterator();  
        while(studentSurveys.hasNext()) {
            StudentSurvey ss = studentSurveys.next();
            surveyHashes.add(ss.getSurveyHash());
        }
        
        assertEquals(10, surveyHashes.size()); //One hash per student
        
        
        //Save an answer and check statistics
        List<Answer> answers = new ArrayList<Answer>();
        for(Subject s : allStudentSurveys.iterator().next().getSubjects()) {
            answers.add(new Answer( s.getId().intValue(), "Cursaria en C1"));
        }
        service.saveAnswer(surveyHashes.get(0), answers);

        SurveyStatistics newStats = service.getAllSurveyStatistics().get(0);        
        
        assertEquals(survey.getPeriod() , newStats.getPeriod());
        assertEquals(1, newStats.getSurveysCompleted());
        assertEquals(20, newStats.getClasses().size());
        
        for(StatisticClass c : newStats.getClasses()) {
            if(c.getName().equals("C1")) {
                assertEquals(1, c.getEnrolled());
            }else {
                assertEquals(0, c.getEnrolled());
            }
        }
        
        
        //Edit a survey and check statistics again
        List<Answer> newAnswers = new ArrayList<Answer>();
        for(Subject s : allStudentSurveys.iterator().next().getSubjects()) {
            if(s.getName().equals("Matematica 1")) {
                newAnswers.add(new Answer(s.getId().intValue(), "Cursaria en C2"));
            }
        }
        service.saveAnswer(surveyHashes.get(0), newAnswers);

        SurveyStatistics newestStats = service.getAllSurveyStatistics().get(0);        
        
        assertEquals(survey.getPeriod() , newestStats.getPeriod());
        assertEquals(1, newestStats.getSurveysCompleted());
        assertEquals(20, newestStats.getClasses().size());
        
        for(StatisticClass c : newestStats.getClasses()) {
            if(c.getName().equals("C1") && !c.getSubject().equals("Matematica 1")) {
                assertEquals(1, c.getEnrolled());
            }else {
                if(c.getName().equals("C2") && c.getSubject().equals("Matematica 1")) {
                    assertEquals(1, c.getEnrolled());
                }else {
                    assertEquals(0, c.getEnrolled());
                }
            }
        }
        

        Iterable<StudentSurvey> allNewestStudentSurveys = studentR.findAll();
        Iterator<StudentSurvey> iterator = allNewestStudentSurveys.iterator();
        List<Subject> thisStudentSubjects = new ArrayList<Subject>();
        while(iterator.hasNext()) {
            StudentSurvey s = iterator.next();
            if(s.getSurveyHash() == surveyHashes.get(1) ) {
                thisStudentSubjects.addAll(s.getSubjects());
            }
        }
        
        //Save a survey with another student
        List<Answer> newestAnswers = new ArrayList<Answer>();
        for(Subject s : thisStudentSubjects) {
            if(s.getName().equals("Matematica 1")) {
                newestAnswers.add(new Answer(s.getId().intValue(), "Cursaria en C2"));
            }else {
                newestAnswers.add(new Answer(s.getId().intValue(), "Cursaria en C1"));
            }
        }
        service.saveAnswer(surveyHashes.get(1), newestAnswers);
        
        SurveyStatistics latestStats = service.getAllSurveyStatistics().get(0);        
        
        assertEquals(survey.getPeriod() , latestStats.getPeriod());
        assertEquals(2, latestStats.getSurveysCompleted());
        assertEquals(20, latestStats.getClasses().size());
        
        for(StatisticClass c : latestStats.getClasses()) {
            if(c.getName().equals("C1") && !c.getSubject().equals("Matematica 1")) {
                assertEquals(2, c.getEnrolled());
            }else {
                if(c.getName().equals("C2") && c.getSubject().equals("Matematica 1")) {
                    assertEquals(2, c.getEnrolled());
                }else {
                    assertEquals(0, c.getEnrolled());
                }
            }
        }
    }

    
    private Survey createSurvey() {
        List<Subject> subjects = createSubjects();
        String emails = "one@gmail.com,two@gmail.com,three@gmail.com,four@gmail.com,five@gmail.com,"
                + "six@gmail.com,seven@gmail.com,eight@gmail.com,nine@gmail.com,ten@gmail.com";
        return new Survey("Primer cuatrimestre 2019", "Esta encuesta es para...", subjects, emails);
    }


    private List<Subject> createSubjects() {
        List<Subject> subjects = new ArrayList<Subject>();
        Subject matematica1 = new Subject("Matematica 1", getThreeClasses(), getThreeClassesOptions(), null);
        Subject matematica2 = new Subject("Matematica 2", getTwoClasses(), getTwoClassesOptions(), null);
        Subject matematica3 = new Subject("Matematica 3", getAClass(), getAClassOptions(), null);
        Subject orga = new Subject("Organizacion de computadoras", getThreeClasses(), getThreeClassesOptions(), null);
        Subject intro = new Subject("Introduccion a la programacion", getThreeClasses(), getThreeClassesOptions(), null);
        Subject concurrente = new Subject("Programacion concurrente", getTwoClasses(), getTwoClassesOptions(), null);
        Subject estru = new Subject("Estructura de datos", getTwoClasses(), getTwoClassesOptions(), null);
        Subject funcional = new Subject("Programacion funcional", getTwoClasses(), getTwoClassesOptions(), null);
        Subject bd = new Subject("Bases de datos", getAClass(), getAClassOptions(), null);
        Subject ingles = new Subject("Ingles 1", getAClass(), getAClassOptions(), null);
        
        subjects.add(matematica1);
        subjects.add(matematica2);
        subjects.add(matematica3);
        subjects.add(orga);
        subjects.add(intro);
        subjects.add(concurrente);
        subjects.add(estru);
        subjects.add(funcional);
        subjects.add(bd);
        subjects.add(ingles);
        
        return subjects;
    }


    private List<String> getAClassOptions() {
        List<String> defaultOptions = getDefaultOptions();
        defaultOptions.add("Cursaria en C1");
        return defaultOptions;
    }
    
    private List<String> getTwoClassesOptions() {
        List<String> options = getAClassOptions();
        options.add("Cursaria en C2");
        return options;
    }
    
    private List<String> getThreeClassesOptions() {
        List<String> options = getTwoClassesOptions();
        options.add("Cursaria en C3");
        return options;
    }
    
    private List<String> getDefaultOptions() {
        List<String> defaultOptions = new ArrayList<String>();
        defaultOptions.add("Ya aprobe");
        defaultOptions.add("Todavia no voy a cursar");
        defaultOptions.add("Quisiera cursar pero no puedo");
        return defaultOptions;
    }


    private List<SubjectClass> getAClass() {
        List<SubjectClass> classes = new ArrayList<SubjectClass>();
        classes.add(new SubjectClass("C1", "profesor,profesora", 30, getSchedules()));
        return classes;
    }
    
    private List<SubjectClass> getTwoClasses() {
        List<SubjectClass> classes = getAClass();
        classes.add(new SubjectClass("C2", "profesor, profesora", 30, getSchedules()));
        return classes;
    }
    
    private List<SubjectClass> getThreeClasses() {
        List<SubjectClass> classes = getTwoClasses();
        classes.add(new SubjectClass("C3", "profesor, profesora", 40, getSchedules()));
        return classes;
    }

    private List<String> getSchedules() {
        List<String> schedules = new ArrayList<String>();
        schedules.add("Jueves 22hs");
        return schedules;
    }

    
    
    
}
