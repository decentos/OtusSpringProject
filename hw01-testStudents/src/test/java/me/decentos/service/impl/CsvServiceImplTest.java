package me.decentos.service.impl;

import me.decentos.service.CsvService;
import me.decentos.service.ResourceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvServiceImplTest {

    ResourceService resourceService;
    CsvService csvService;

    @BeforeEach
    void setUp() {
        resourceService = new ResourceServiceImpl();
        csvService = new CsvServiceImpl(resourceService);
    }

    @DisplayName("Метод getQuestionsList() должен возвращать список класса ArrayList")
    @Test
    void getQuestionsList() {
        assertEquals(csvService.getQuestionsList().getClass(), ArrayList.class);
    }
}