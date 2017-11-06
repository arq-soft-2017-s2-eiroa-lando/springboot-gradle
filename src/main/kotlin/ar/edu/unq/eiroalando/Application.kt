package ar.edu.unq.eiroalando

import ar.edu.unq.eiroalando.model.Student
import ar.edu.unq.eiroalando.model.Subject
import ar.edu.unq.eiroalando.persistence.StudentRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import ar.edu.unq.eiroalando.persistence.SubjectRepository
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@SpringBootApplication
open class Application {
    val log = LoggerFactory.getLogger(Application::class.java)


    @Bean
    open fun init(subjectRepository: SubjectRepository, studentRepository: StudentRepository) = CommandLineRunner {

        val s1 = Subject("Intro",true,false)
        val s2 = Subject("Matematica 1",false,false)
        val s3 = Subject("Orga",false,false)
        val s4 = Subject("Ingles 1",false,false)
        val s5 = Subject("Objetos 1",false,false)

        val allSubjects = listOf<Subject>(s1,s2,s3,s4,s5)

        var lucas  =  Student("Lucas",1)

        allSubjects.forEach { sub -> subjectRepository.save(sub) }

        lucas.subjects.addAll(allSubjects)

        studentRepository.save(lucas)

        log.info(" ===========  Initial Data Loaded ===========")
    }

}

@Configuration
@EnableWebMvc
open class WebConfig : WebMvcConfigurerAdapter() {
    override fun addCorsMappings(registry: CorsRegistry?) {
        registry!!.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("PUT", "DELETE", "GET", "POST")
                .allowCredentials(false).maxAge(3600)
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}