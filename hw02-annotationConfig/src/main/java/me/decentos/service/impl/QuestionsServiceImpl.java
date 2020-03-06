package me.decentos.service.impl;

import me.decentos.domain.Questions;
import me.decentos.service.CsvService;
import me.decentos.service.QuestionsService;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class QuestionsServiceImpl implements QuestionsService {
    private final CsvService csvService;
    private int totalScore;
    private boolean isRuLanguage = Locale.getDefault().getLanguage().equals("ru");
    private String name = isRuLanguage ? "Введите своё имя:" : "Enter your name:";
    private String getReady = isRuLanguage ? ", приготовьтесь ответить на несколько вопросов!" : ", get ready to answer a few questions!";
    private String trueAnswer = isRuLanguage ? "Это правильный ответ! Всего правильных ответов: " : "This is the correct answer! Total correct answers: ";
    private String falseAnswer = isRuLanguage ? "Ошибка! Правильный ответ: " : "Error! Correct answer: ";
    private String result = isRuLanguage ? ", количество правильных ответов за весь тест из 5 вопросов: " : ", number of correct answers to the all test of 5 questions: ";

    public QuestionsServiceImpl(final CsvService csvService) {
        this.csvService = csvService;
    }

    @Override
    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(name);
            String userName = scanner.nextLine();
            System.out.println(userName + getReady);
            Thread.sleep(2000);

            List<Questions> questionsList = csvService.getQuestionsList();
            for (Questions question : questionsList) {
                System.out.println(question.getQuestion());
                String userAnswer = scanner.nextLine();
                if (userAnswer.equalsIgnoreCase(question.getAnswer())) {
                    totalScore++;
                    System.out.println(trueAnswer + totalScore);
                }
                else {
                    System.out.println(falseAnswer + question.getAnswer());
                }
            }

            System.out.println(userName + result + totalScore);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
