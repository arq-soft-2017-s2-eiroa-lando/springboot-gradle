package application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import application.model.Student;
import application.model.Subject;
import application.persistence.StudentRepository;
import application.persistence.SubjectRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(SubjectRepository subjRepo, StudentRepository studRepo) {
		return (args) -> {

			List<Subject> subjects = new ArrayList<Subject>();
			
			Subject s1 = new Subject("Introduccion a la programacion", true, false);
			Subject s2 = new Subject("Matematica 1", false, false);
			Subject s3 = new Subject("Organizacion y arquitectura de computadoras", false, false);
			Subject s4 = new Subject("Ingles 1", false, false);
			Subject s5 = new Subject("Objetos 1", false, false);
			
			subjects.add(s1);subjects.add(s2);subjects.add(s3);subjects.add(s4);subjects.add(s5); 

			for(Subject s : subjects){
				subjRepo.save(s);
			}
			
			Student s = new Student("Lucas", 1);
			s.getSubjects().addAll(subjects);
			studRepo.save(s);
		};
	}
	
	@Configuration
    @EnableWebMvc
    public class WebConfig extends WebMvcConfigurerAdapter {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("PUT", "DELETE", "GET", "POST")
                    .allowCredentials(false).maxAge(3600);
            }
     }
	
}
