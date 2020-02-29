package me.decentos.service.impl;

import me.decentos.service.ResourceService;

import java.net.URL;

public class ResourceServiceImpl implements ResourceService {
    private static final String CSV_RESOURCES = "questions.csv";

    @Override
    public URL getCsvURL() {
        return getClass().getClassLoader().getResource(CSV_RESOURCES);
    }
}
