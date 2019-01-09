package com.todoApp.todoApp;

import com.todoApp.todoApp.controller.UserController;
import java.io.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan({"com.todoApp.todoApp","controller"})
public class TodoAppApplication {

	public static void main(String[] args) {
                new File(UserController.uploadDirectory).mkdir();
		SpringApplication.run(TodoAppApplication.class, args);
	}
}
