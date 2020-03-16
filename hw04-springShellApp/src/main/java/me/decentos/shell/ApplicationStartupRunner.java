package me.decentos.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
@Order(InteractiveShellApplicationRunner.PRECEDENCE - 1000)
public class ApplicationStartupRunner implements CommandLineRunner {

    private final MessageSource messageSource;

    @Override
    public void run(String... args) throws Exception {
        String name = messageSource.getMessage("user.name", null, Locale.getDefault());
        System.out.println(name);
    }
}
