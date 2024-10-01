package br.unipar.tasketracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class TaskeTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskeTrackerApplication.class, args);
    }


}
