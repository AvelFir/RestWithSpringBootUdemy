package br.com.azdev.restwithspringbootudemy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class RestWithSpringBootUdemyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestWithSpringBootUdemyApplication.class, args);
	}

}
