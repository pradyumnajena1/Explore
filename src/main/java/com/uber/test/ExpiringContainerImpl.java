package com.uber.test;

import java.time.LocalDateTime;
import java.util.*;

public class ExpiringContainerImpl implements ExpiringContainer{

    private final Map<String, Deque<ItemNode>> container;
    private final PriorityQueue<ItemNode> priorityQueue;

    private int totalCounter;

    public ExpiringContainerImpl() {
        container = new HashMap<>();
        priorityQueue = new PriorityQueue<>();
        totalCounter = 0;
        Thread thread = new Thread(new ObjectExpirer( ));
        thread.start();


    }

    @Override
    public void put(String value, LocalDateTime expiredOn) {
        ItemNode itemNode = new ItemNode(value, expiredOn);
        doAddItem(itemNode);
    }

    @Override
    public synchronized int getCount(String value) {
        return container.getOrDefault(value, new ArrayDeque<>()).size();
    }

    @Override
    public synchronized int getTotalCount() {
        return totalCounter;
    }

    private synchronized void removeItem(ItemNode node){
        Deque<ItemNode> itemNodes = container.getOrDefault(node.getName(), new ArrayDeque<>());
        while (itemNodes.size()>0 &&
                ( itemNodes.peekLast().getDateTime().isBefore(node.getDateTime()) ||
                itemNodes.peekLast().getDateTime().equals(node.getDateTime()))){
            itemNodes.pollLast();
            totalCounter--;
        }
    }

    private synchronized void doAddItem(ItemNode node){
        Deque<ItemNode> itemContainer = container.getOrDefault(node.getName(), new ArrayDeque<>());
        itemContainer.offerFirst(node);
        container.put(node.getName(),itemContainer);
        priorityQueue.offer(node);
        totalCounter++;
    }

    private   class ObjectExpirer implements Runnable {


        public ObjectExpirer( ) {

        }

        @Override
        public void run() {

            while (true){

                ItemNode topItem = getPeek();
                if(topItem != null) {
                    LocalDateTime now = LocalDateTime.now();
                    if (topItem.getDateTime().isBefore(now)) {
                        System.out.println("found item to remove");
                        ItemNode poll = getPoll();
                        System.out.println(poll);
                        removeItem(poll);
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private synchronized ItemNode getPoll() {
        return priorityQueue.poll();
    }

    private synchronized ItemNode getPeek() {
        return priorityQueue.peek();
    }
}
