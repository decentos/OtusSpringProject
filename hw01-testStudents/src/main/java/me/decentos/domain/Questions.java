package me.decentos.domain;

import com.opencsv.bean.CsvBindByName;

public class Questions {

    @CsvBindByName(column = "Question")
    private String question;

    @CsvBindByName(column = "Answer")
    private String answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(final String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(final String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
