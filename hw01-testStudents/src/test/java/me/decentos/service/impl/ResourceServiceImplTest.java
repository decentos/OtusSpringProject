package me.decentos.service.impl;

import me.decentos.service.ResourceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResourceServiceImplTest {

    ResourceService resourceService;

    @BeforeEach
    void setUp() {
        resourceService = new ResourceServiceImpl();
        assert resourceService.getCsvURL() != null;
    }

    @DisplayName("Метод getCsvURL() должен вернуть ссылку типа URL")
    @Test
    void getCsvURL() {
        assertEquals(resourceService.getCsvURL().getClass(), URL.class);
    }

    @DisplayName("ResourceService должен csv-файл c именем 'questions.csv'")
    @Test
    void getCsvFile() {
        assertEquals(new File(resourceService.getCsvURL().getFile()).getName(), "questions.csv");
    }
}