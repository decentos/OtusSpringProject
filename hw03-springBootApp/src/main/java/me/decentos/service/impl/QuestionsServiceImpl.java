package me.decentos.service.impl;

import me.decentos.domain.Questions;
import me.decentos.service.CsvService;
import me.decentos.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Service
public class QuestionsServiceImpl implements QuestionsService {
    private final CsvService csvService;
    private final MessageSource messageSource;
    private int totalScore;

    @Autowired
    public QuestionsServiceImpl(final CsvService csvService, final MessageSource messageSource) {
        this.csvService = csvService;
        this.messageSource = messageSource;
    }

    @Override
    public void run() {
        String name = messageSource.getMessage("user.name", null, Locale.getDefault());
        String getReady = messageSource.getMessage("get.ready", null, Locale.getDefault());
        String trueAnswer = messageSource.getMessage("true.answer", null, Locale.getDefault());
        String falseAnswer = messageSource.getMessage("false.answer", null, Locale.getDefault());
        String result = messageSource.getMessage("result", null, Locale.getDefault());

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
