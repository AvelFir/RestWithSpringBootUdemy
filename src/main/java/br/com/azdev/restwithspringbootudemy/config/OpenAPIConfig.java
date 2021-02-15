package br.com.azdev.restwithspringbootudemy.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                    .title("RESTful API With Spring 2.4.2")
                    .version("v1")
                    .description("Some description about your API")
                    .termsOfService( "http:swagger.io/terms/")
                    .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
