package me.decentos;

import me.decentos.config.AppConfig;
import me.decentos.service.CsvService;
import me.decentos.service.QuestionsService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CsvService csvService = context.getBean(CsvService.class);
        QuestionsService questionsService = context.getBean(QuestionsService.class);

        questionsService.run();
    }
}
