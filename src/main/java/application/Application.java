package application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import application.model.Subject;
import application.persistence.SubjectRepository;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(SubjectRepository repository) {
		return (args) -> {

			repository.save(new Subject("Intro"));
			repository.save(new Subject("Matematica 1"));
			repository.save(new Subject("Orga"));
			repository.save(new Subject("Ingles 1"));
			repository.save(new Subject("Objetos 1"));

			log.info("Subjects found with findAll():");
			log.info("-------------------------------");
			for (Subject s : repository.findAll()) {
				log.info(s.getName());
			}
			log.info("");

			Subject s = repository.findOne(1L);
			log.info("Subject found with findOne(1L):");
			log.info("--------------------------------");
			log.info(s.getName());
			log.info("");

			log.info("Subject found with findByName('Orga'):");
			log.info("--------------------------------------------");
			for (Subject su : repository.findByName("Orga")) {
				log.info(su.toString());
			}
			log.info("");
		};
	}
	
	@Configuration
    @EnableWebMvc
    public class WebConfig extends WebMvcConfigurerAdapter {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("http://localhost:4200")
                    .allowedMethods("PUT", "DELETE", "GET", "POST")
                    .allowCredentials(false).maxAge(3600);
            }
     }
	
}
