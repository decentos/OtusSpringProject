package me.decentos.service.impl;

import me.decentos.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.net.URL;
import java.util.Locale;

public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private MessageSource messageSource;

    @Override
    public URL getCsvURL() {
        return getClass().getClassLoader().getResource(getCsvResources());
    }

    private String getCsvResources() {
        return messageSource.getMessage("questions.csv", null, Locale.getDefault());
    }
}
