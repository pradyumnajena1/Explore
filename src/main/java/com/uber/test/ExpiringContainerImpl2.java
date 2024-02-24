package com.uber.test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ExpiringContainerImpl2 implements ExpiringContainer {

    private ConcurrentHashMap<String, AtomicInteger> concurrentHashMap;
    private AtomicInteger totalCount = new AtomicInteger(0);
    private Timer timer;

    public ExpiringContainerImpl2() {
        concurrentHashMap = new ConcurrentHashMap<>();
        timer = new Timer(true);
    }

    @Override
    public void put(String value, TimeUnit unit, int num) {
        concurrentHashMap.computeIfAbsent(value, v -> new AtomicInteger(0)).incrementAndGet();
        totalCount.incrementAndGet();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                remove(value);
            }
        }, unit.toMillis(num));

    }

    private void remove(String value) {
        concurrentHashMap.computeIfPresent(value, (k, v) -> {
            if (v.get() > 1) {
                totalCount.decrementAndGet();
                return new AtomicInteger(v.decrementAndGet());
            } else {
                return null;
            }
        });

    }

    @Override
    public int getCount(String value) {
        return concurrentHashMap.getOrDefault(value, new AtomicInteger(0)).get();
    }

    @Override
    public int getTotalCount() {
        return totalCount.get();
    }
}
