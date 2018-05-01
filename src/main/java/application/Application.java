package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Configuration
	@EnableSwagger2
	public class SwaggerConfig {
		@Bean
		public Docket api() {
			return new Docket(DocumentationType.SWAGGER_2).select()
					.apis(RequestHandlerSelectors.basePackage("application.controller")).paths(PathSelectors.any())
					.build().useDefaultResponseMessages(false).apiInfo(apiInfo());
		}

		@SuppressWarnings("deprecation")
		private ApiInfo apiInfo() {
			return new ApiInfo("API sistema de inscripcion UNQ TPI/LIDS",
					"API para el sistema de inscripcion a materias de las carreras"
							+ " TPI y LIDS de la Universidad de Quilmes",
					"", "Terms of service", null, "", "");
		}

	}

	@Configuration
	public class WebMvcConfig extends WebMvcConfigurerAdapter {

		@Bean
		public AuthenticationInterceptor authenticationInterceptor() {
		    return new AuthenticationInterceptor();
		}
		
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/api/statistics", "/api/survey");
		}

	}
}
