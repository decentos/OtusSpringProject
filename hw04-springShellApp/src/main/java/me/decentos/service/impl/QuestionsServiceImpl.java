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
        String trueAnswer = messageSource.getMessage("true.answer", null, Locale.getDefault());
        String falseAnswer = messageSource.getMessage("false.answer", null, Locale.getDefault());
        Scanner scanner = new Scanner(System.in);

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
        scanner.close();
    }

    @Override
    public int getTotalScore() {
        return totalScore;
    }
}
