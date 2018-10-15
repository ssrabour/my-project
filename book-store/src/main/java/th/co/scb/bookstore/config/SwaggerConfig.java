package th.co.scb.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("th.co.scb.bookstore.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    
    private ApiInfo getApiInfo() {
        Contact contact = new Contact("Supakorn Srabour", null, "peat2009@hotmail.com");
        return new ApiInfoBuilder()
                .title("Book Store API Documentation")
				.description("RESTful API for a bookstore that allows a user to login, perform user "
						+ "related tasks, view a list of books and place book orders")
                .version("1.0.0")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .contact(contact)
                .build();
    }
}