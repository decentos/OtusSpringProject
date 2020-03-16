package me.decentos.service.impl;

import me.decentos.service.ResourceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.io.File;
import java.net.URL;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ResourceServiceImplTest {

    ResourceService resourceService;
    ReloadableResourceBundleMessageSource messageSource;


    @BeforeEach
    void setUp() {
        messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("i18n/bundle");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setDefaultLocale(Locale.ENGLISH);
        resourceService = new ResourceServiceImpl(messageSource);
        assert resourceService.getCsvURL() != null;
    }

    @DisplayName("Метод getCsvURL() должен вернуть ссылку типа URL")
    @Test
    void getCsvURL() {
        assertEquals(resourceService.getCsvURL().getClass(), URL.class);
    }

    @DisplayName("ResourceService должен csv-файл c именем 'questions_ru.csv' для России")
    @Test
    void getCsvFile() {
        assertEquals(new File(resourceService.getCsvURL().getFile()).getName(), "questions_ru.csv");
    }

    @DisplayName("Метод getCsvResources() должен вовзвращать разные csv-файлы, в зависимости от текущей локали")
    @Test
    void getCsvResources() {
        String csvFileRu = messageSource.getMessage("questions.csv", null, Locale.getDefault());
        String csvFileEn = messageSource.getMessage("questions.csv", null, Locale.ENGLISH);

        assertEquals(csvFileRu, "questions_ru.csv");
        assertEquals(csvFileEn, "questions_en.csv");
    }
}