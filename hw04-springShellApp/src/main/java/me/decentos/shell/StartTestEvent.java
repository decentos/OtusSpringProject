package me.decentos.shell;

import lombok.RequiredArgsConstructor;
import me.decentos.service.QuestionsService;
import org.springframework.context.MessageSource;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.Locale;

@ShellComponent
@RequiredArgsConstructor
public class StartTestEvent {

    private final QuestionsService service;
    private final MessageSource messageSource;
    private String userName;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "AnyUser") String userName) {
        String getReady = messageSource.getMessage("get.ready", null, Locale.getDefault());
        this.userName = userName;
        return String.format("%s%s", userName, getReady);
    }

    @ShellMethod(value = "Start test command", key = {"test", "start"})
    @ShellMethodAvailability(value = "isStartTestAvailable")
    public String startTest() {
        String result = messageSource.getMessage("result", null, Locale.getDefault());
        service.run();
        return String.format("%s%s%d", userName, result, service.getTotalScore());
    }

    private Availability isStartTestAvailable() {
        return userName == null ? Availability.unavailable("Для начала тестирования необходимо войти!") : Availability.available();
    }
}
