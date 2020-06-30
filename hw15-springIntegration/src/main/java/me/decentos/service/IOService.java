package me.decentos.service;

public interface IOService {

    void printMsg(String s);

    void printMsg(String format, Object... args);
}
