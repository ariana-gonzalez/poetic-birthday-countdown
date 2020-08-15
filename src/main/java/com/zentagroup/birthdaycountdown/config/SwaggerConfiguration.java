package com.zentagroup.birthdaycountdown.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import java.util.Collections;

/**
 * Ariana González
 * ariana.sgm5@gmail.com
 */

/**
 * Swagger Configuration
 * UI available at: http://localhost:8090/swagger-ui.html#/
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/api.*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Poetic Birthday Countdown",
                "Birthday countdown API",
                "v1.0",
                "N/A",
                new Contact("Ariana Gonzalez", "", "ariana.sgm5@gmail.com"),
                "N/A", "N/A", Collections.emptyList());
    }

}
