package me.decentos.config;

import me.decentos.service.CsvService;
import me.decentos.service.QuestionsService;
import me.decentos.service.ResourceService;
import me.decentos.service.impl.CsvServiceImpl;
import me.decentos.service.impl.QuestionsServiceImpl;
import me.decentos.service.impl.ResourceServiceImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

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

    @Bean
    MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("bundle");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setDefaultLocale(Locale.ENGLISH);
        return messageSource;
    }

}
