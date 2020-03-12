package me.decentos.service.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import me.decentos.domain.Questions;
import me.decentos.service.CsvService;
import me.decentos.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class CsvServiceImpl implements CsvService {
    private final ResourceService resourceService;

    @Autowired
    public CsvServiceImpl(final ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Override
    public List<Questions> getQuestionsList() {
        List<Questions> beans = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceService.getCsvURL().openStream()))){
            beans = new CsvToBeanBuilder<Questions>(reader).withType(Questions.class).build().parse();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return beans;
    }
}
