package me.decentos;

import me.decentos.config.AppConfig;
import me.decentos.service.QuestionsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        QuestionsService service = context.getBean(QuestionsService.class);
        service.run();
    }
}
