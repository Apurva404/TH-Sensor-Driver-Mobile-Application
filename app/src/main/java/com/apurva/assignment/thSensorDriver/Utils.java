package com.apurva.assignment.thSensorDriver;
import java.util.Random;


public class Utils {
    public static int generateRandomTemperature() {
        Random r = new Random();
        return r.nextInt(150 - 50) + 50;
    }

    public static int generateRandomHumidity(){
        Random r = new Random();
        return r.nextInt(150 - 50) + 50;
    }

    public static void sleepForSeconds(int secs) {
        try {
            Thread.sleep(secs * 1000);
        } catch(InterruptedException x) {
            throw new RuntimeException("interrupted",x);
        }
    }

    public static long getThreadId() {
        Thread t = Thread.currentThread();
        return t.getId();
    }
}
