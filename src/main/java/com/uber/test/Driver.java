package com.uber.test;

import java.time.LocalDateTime;

public class Driver {
    public static void main(String[] args) {
        ExpiringContainer container = new ExpiringContainerImpl();
        container.put("apple" , LocalDateTime.now().plusSeconds(10));
        container.put("apple", LocalDateTime.now().plusSeconds(20));
        container.put("apple", LocalDateTime.now().plusSeconds(30));
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
