package me.decentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class SpringActuatorApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringActuatorApp.class, args);
    }
}
