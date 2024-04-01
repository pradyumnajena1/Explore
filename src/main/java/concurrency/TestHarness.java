package concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestHarness {

    public static void main(String[] args) throws InterruptedException {
        long timeTasks = new TestHarness().timeTasks(5, () -> {
            System.out.println(Thread.currentThread().getName());
        });
        System.out.println(timeTasks);
    }


    public long timeTasks(int numThreads,Runnable task) throws InterruptedException {
        CountDownLatch startGate = new CountDownLatch(1);
        CountDownLatch endGate = new CountDownLatch(numThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        for(int i=0;i<numThreads;i++){
            executorService.submit(()->{
                try {
                    startGate.await();
                    task.run();
                    endGate.countDown();
                } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
                }

            });
            Thread.sleep(1000);
        }
        long time = System.currentTimeMillis();
        startGate.countDown();
        endGate.await();
        long timeTaken = System.currentTimeMillis() - time;
        executorService.shutdownNow();
        return timeTaken;

    }

}
