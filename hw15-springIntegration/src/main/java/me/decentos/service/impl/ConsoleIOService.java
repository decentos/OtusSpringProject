package me.decentos.service.impl;

import me.decentos.service.IOService;
import org.springframework.stereotype.Service;

@Service
public class ConsoleIOService implements IOService {

    public void printMsg(String s) {
        System.out.println(s);
    }

    public void printMsg(String format, Object... args) {
        System.out.printf(format, args);
    }
}