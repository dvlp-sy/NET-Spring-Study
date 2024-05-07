package net.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Application {

	ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
