package me.decentos.shell;

import lombok.RequiredArgsConstructor;
import me.decentos.service.OrdersGeneratorService;
import me.decentos.service.impl.SleepService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {

    private final OrdersGeneratorService ordersService;

    @ShellMethod(value = "Start infinite generate orders", key = {"run", "start", "1"})
    private void runGenerator() {

        while (true) {
            ordersService.processNewOrder();
            SleepService.sleepUnintelligibly(2);
        }
    }
}
