package ar.edu.unq.eiroalando

import ar.edu.unq.eiroalando.model.Subject
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
    open fun init(repository: SubjectRepository) = CommandLineRunner {
        repository.save(Subject("Intro"))
        repository.save(Subject("Matematica 1"))
        repository.save(Subject("Orga"))
        repository.save(Subject("Ingles 1"))
        repository.save(Subject("Objetos 1"))

        log.info("Subjects found with findAll():")
        log.info("-------------------------------")
        for (s in repository.findAll()) {
            log.info(s.name)
        }
        log.info("")

        val s = repository.findOne(1L)
        log.info("Subject found with findOne(1L):")
        log.info("--------------------------------")
        log.info(s.name)
        log.info("")

        log.info("Subject found with findByName('Orga'):")
        log.info("--------------------------------------------")
        for (su in repository.findByName("Orga")) {
            log.info(su.toString())
        }
        log.info("")
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
    SpringApplication.run(Application::class.java)
}