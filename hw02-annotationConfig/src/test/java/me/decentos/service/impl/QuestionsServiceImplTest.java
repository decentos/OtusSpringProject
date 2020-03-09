package me.decentos.service.impl;

import me.decentos.service.CsvService;
import me.decentos.service.QuestionsService;
import me.decentos.service.ResourceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionsServiceImplTest {

    QuestionsService questionsService;
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
        questionsService = new QuestionsServiceImpl(csvService, messageSource);
    }

    @DisplayName("Метод должен возвращать корректный userName, в зависимости от текущей локали")
    @Test
    void getUserNameFromBundle() {
        String userNameRu = messageSource.getMessage("user.name", null, Locale.getDefault());
        String userNameEn = messageSource.getMessage("user.name", null, Locale.ENGLISH);

        assertEquals(userNameRu, "Введите своё имя:");
        assertEquals(userNameEn, "Enter your name:");
    }

    @DisplayName("Метод должен возвращать корректный getReady, в зависимости от текущей локали")
    @Test
    void getGetReadyFromBundle() {
        String getReadyRu = messageSource.getMessage("get.ready", null, Locale.getDefault());
        String getReadyEn = messageSource.getMessage("get.ready", null, Locale.ENGLISH);

        assertEquals(getReadyRu, ", приготовьтесь ответить на несколько вопросов!");
        assertEquals(getReadyEn, ", get ready to answer a few questions!");
    }

    @DisplayName("Метод должен возвращать корректный trueAnswer, в зависимости от текущей локали")
    @Test
    void getTrueAnswerFromBundle() {
        String trueAnswerRu = messageSource.getMessage("true.answer", null, Locale.getDefault());
        String trueAnswerEn = messageSource.getMessage("true.answer", null, Locale.ENGLISH);

        assertEquals(trueAnswerRu, "Это правильный ответ! Всего правильных ответов: ");
        assertEquals(trueAnswerEn, "This is the correct answer! Total correct answers: ");
    }

    @DisplayName("Метод должен возвращать корректный falseAnswer, в зависимости от текущей локали")
    @Test
    void getFalseAnswerFromBundle() {
        String falseAnswerRu = messageSource.getMessage("false.answer", null, Locale.getDefault());
        String falseAnswerEn = messageSource.getMessage("false.answer", null, Locale.ENGLISH);

        assertEquals(falseAnswerRu, "Ошибка! Правильный ответ: ");
        assertEquals(falseAnswerEn, "Error! Correct answer: ");
    }

    @DisplayName("Метод должен возвращать корректный result, в зависимости от текущей локали")
    @Test
    void getResultFromBundle() {
        String resultRu = messageSource.getMessage("result", null, Locale.getDefault());
        String resultEn = messageSource.getMessage("result", null, Locale.ENGLISH);

        assertEquals(resultRu, ", количество правильных ответов за весь тест из 5 вопросов: ");
        assertEquals(resultEn, ", number of correct answers to the all test of 5 questions: ");
    }

}