package me.decentos.service.impl;

import me.decentos.service.CsvService;
import me.decentos.service.ResourceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.ArrayList;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvServiceImplTest {

    ResourceService resourceService;
    CsvService csvService;
    ReloadableResourceBundleMessageSource messageSource;

    @BeforeEach
    void setUp() {
        messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("bundle");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setDefaultLocale(Locale.ENGLISH);
        resourceService = new ResourceServiceImpl(messageSource);
        csvService = new CsvServiceImpl(resourceService);
    }

    @DisplayName("Метод getQuestionsList() должен возвращать список класса ArrayList")
    @Test
    void getQuestionsList() {
        assertEquals(csvService.getQuestionsList().getClass(), ArrayList.class);
    }
}