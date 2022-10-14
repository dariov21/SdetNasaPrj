package org.dario.ui;

import java.util.Calendar;

public class Waits {
    public static void waitForSeconds(long waitSeconds){
        waitSeconds = waitSeconds * 1000;
        Calendar currentTime = Calendar.getInstance();
        long currentTimeMillis = currentTime.getTimeInMillis();
        long secCounter = 0;
        while (secCounter < waitSeconds) {
            Calendar newTime = Calendar.getInstance();
            secCounter = (newTime.getTimeInMillis()) - (currentTimeMillis);
        }
    }
}
