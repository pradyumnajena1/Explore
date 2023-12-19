package com.uber.test;

import java.time.LocalDateTime;

public class ItemNode implements Comparable<ItemNode>{
    private final String name;

    private final LocalDateTime dateTime;

    public ItemNode(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public int compareTo(ItemNode o) {
        if( this.dateTime.isBefore(o.getDateTime())){
            return -1;
        }else if(this.dateTime.isAfter(o.getDateTime())){
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ItemNode{");
        sb.append("name='").append(name).append('\'');
        sb.append(", dateTime=").append(dateTime);
        sb.append('}');
        return sb.toString();
    }
}
