package com.uber.test;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public interface ExpiringContainer {

    public void put(String value, TimeUnit timeUnit,int num);
    public int getCount(String value);
    public int getTotalCount();
}
