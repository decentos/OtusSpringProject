package me.decentos.service.impl;

import me.decentos.domain.Questions;
import me.decentos.service.CsvService;
import me.decentos.service.QuestionsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionsServiceImpl implements QuestionsService {
    private CsvService csvService;
    private List<String> userAnswer;
    private int totalScore;

    public QuestionsServiceImpl(final CsvService csvService) {
        this.csvService = csvService;
        userAnswer = new ArrayList<>();
    }

    @Override
    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите своё имя: ");
            String userName = scanner.nextLine();
            System.out.println(userName + ", приготовьтесь ответить на несколько вопросов!");
            Thread.sleep(2000);

            List<Questions> questionsList = csvService.getQuestionsList();
            for (Questions question : questionsList) {
                System.out.println(question.getQuestion());
                String userAnswer = scanner.nextLine();
                if (userAnswer.equalsIgnoreCase(question.getAnswer())) {
                    totalScore++;
                    System.out.println("Это правильный ответ! Всего правильных ответов: " + totalScore);
                }
                else {
                    System.out.println("Ошибка! Правильный ответ: " + question.getAnswer());
                }
            }

            System.out.println(userName + ", количество правильных ответов за весь тест: " + totalScore + " из 5");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
