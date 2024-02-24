package com.uber.test;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class Driver {
    public static void main(String[] args) {
        ExpiringContainer container = new ExpiringContainerImpl2();
        container.put("apple" , TimeUnit.SECONDS,10);
        container.put("apple", TimeUnit.SECONDS,20);
        container.put("apple", TimeUnit.SECONDS,30);
        int appleCount = container.getCount("apple");
        System.out.println(appleCount);
        try {
            Thread.sleep(1000*15);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        appleCount = container.getCount("apple");
        System.out.println(appleCount);
    }
}
