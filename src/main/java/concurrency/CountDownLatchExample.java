package concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatchExample example = new CountDownLatchExample();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i=0;i<5;i++){
            executorService.submit(()->{
                try {
                    example.process();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            Thread.sleep(1000);
        }

        executorService.awaitTermination(5, TimeUnit.SECONDS);
        executorService.shutdown();
    }
    private CountDownLatch latch = new CountDownLatch(5);

    public void process() throws InterruptedException {
        latch.countDown();
        latch.await();
        System.out.println(Thread.currentThread().getName()+ System.currentTimeMillis());
    }
}
