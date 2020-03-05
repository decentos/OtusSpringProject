package me.decentos.config;

import me.decentos.service.CsvService;
import me.decentos.service.QuestionsService;
import me.decentos.service.ResourceService;
import me.decentos.service.impl.CsvServiceImpl;
import me.decentos.service.impl.QuestionsServiceImpl;
import me.decentos.service.impl.ResourceServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
public class AppConfig {

    @Bean
    ResourceService resourceService() {
        return new ResourceServiceImpl();
    }

    @Bean
    CsvService csvService(ResourceService resourceService) {
        return new CsvServiceImpl(resourceService);
    }

    @Bean
    QuestionsService questionsService(CsvService csvService) {
        return new QuestionsServiceImpl(csvService);
    }

}
