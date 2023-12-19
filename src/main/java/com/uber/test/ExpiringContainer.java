package com.uber.test;

import java.time.LocalDateTime;

public interface ExpiringContainer {

    public void put(String value, LocalDateTime expiredOn);
    public int getCount(String value);
    public int getTotalCount();
}
