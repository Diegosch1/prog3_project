package co.edu.uptc.the_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"co.edu.uptc.the_project.services", "co.edu.uptc.the_project.controller"})
public class TheProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(TheProjectApplication.class, args);
    }
}

