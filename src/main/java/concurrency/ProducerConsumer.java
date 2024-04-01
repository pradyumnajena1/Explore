package concurrency;

import java.util.concurrent.BlockingDeque;

public class ProducerConsumer<T> {
    private BlockingDeque<T> queue;

    private static class Consumer<T> implements Runnable {
        private BlockingDeque<T> queue;

        public Consumer(BlockingDeque<T> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    T task = queue.take();
                    System.out.println(task);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static class Producer<T> implements Runnable {
        private BlockingDeque<T> queue;

        public Producer(BlockingDeque<T> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {

            try {
                while (true) {
                    T task = generateTask();
                    queue.put(task);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }


        private T generateTask() {
            return null;
        }
    }


}
