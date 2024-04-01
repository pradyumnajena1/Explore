package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DinningPhilosopher {

    public static void main(String[] args) throws InterruptedException {
        ChopStick[] chopSticks = new ChopStick[3];
        for (int i = 0; i < 3; i++) {
            chopSticks[i] = new ChopStick(i);
        }
        Philosopher[] philosophers = new Philosopher[3];
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            philosophers[i] = new Philosopher(i, chopSticks[i], chopSticks[(i + 1) % 3]);
            executorService.submit(philosophers[i]);
        }
        Thread.sleep(10000);
        executorService.shutdown();
        executorService.awaitTermination(1000, TimeUnit.SECONDS);
    }

    private static class ChopStick {
        private int id;

        public ChopStick(int id) {
            this.id = id;
        }
    }

    private static class Philosopher implements Runnable {
        private ChopStick left;
        private ChopStick right;
        private int id;

        public Philosopher(int id, ChopStick left, ChopStick right) {
            this.left = left;
            this.right = right;
            this.id = id;
        }

        public void eat() throws InterruptedException {
            ChopStick first = left.id < right.id ? left : right;
            ChopStick second = left.id < right.id ? right : left;

            synchronized (first) {
                synchronized (second) {
                    doEat();
                }
            }
        }

        private void doEat() throws InterruptedException {

            System.out.println("philospher id:" + id + " start eating using fork " + left.id + " and " + right.id);
            Thread.sleep((long) (Math.random() * 1000));
            System.out.println("philospher id:" + id + " end eating using fork " + left.id + " and " + right.id);

        }

        @Override
        public void run() {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                try {
                    eat();
                    thinking();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        private void thinking() throws InterruptedException {

            System.out.println("philospher id " + id + " thinking start");
            Thread.sleep((long) (Math.random() * 1000));
            System.out.println("philospher id " + id + " thinking end");

        }
    }
}
