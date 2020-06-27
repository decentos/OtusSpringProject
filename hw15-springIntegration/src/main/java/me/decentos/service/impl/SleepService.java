package me.decentos.service.impl;

import java.util.concurrent.TimeUnit;

public class SleepService {

    public static void sleepUnintelligibly(long sleepFor) {
        boolean interrupted = false;
        try {
            TimeUnit.SECONDS.sleep(sleepFor);
        }
        catch (InterruptedException e) {
            interrupted = true;
        }
        finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
