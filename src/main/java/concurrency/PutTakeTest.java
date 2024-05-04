package concurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PutTakeTest {
    private static ExecutorService pool = Executors.newCachedThreadPool();
    private final BoundedBuffer<Integer> bb;
    private final CyclicBarrier barrier;
    private final AtomicInteger putSum;
    private final AtomicInteger getSum;
    private final int numPairs;
    private final int numTries;

    public PutTakeTest(int capacity, int numPairs, int numTries) {
        this.numPairs = numPairs;
        this.numTries = numTries;
        bb = new BoundedBuffer<>(capacity);
        barrier = new CyclicBarrier(2 * numPairs + 1);
        putSum = new AtomicInteger(0);
        getSum = new AtomicInteger(0);
    }

    public static void main(String[] args)   {
        PutTakeTest test = new PutTakeTest(10, 5, 10);
        test.test();
    }

    public void test() {

        try {
            for (int i = 0; i < numPairs; i++) {
                pool.submit(new Producer(bb, numTries, putSum, barrier));
                pool.submit(new Consumer(bb, numTries, getSum, barrier));
            }
            System.out.println(Thread.currentThread().getName()+" main waiting for start");
            barrier.await();// first wait to start

           // barrier.reset();
            System.out.println(Thread.currentThread().getName()+" main waiting for end");

            barrier.await();//second wait to end

            assert putSum.get() == getSum.get();
            System.out.println(putSum.get());
            System.out.println(getSum.get());
            System.out.println(Thread.currentThread().getName()+" main finished");
            pool.shutdown();
            pool.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }


    }

    private static class Producer implements Runnable {

        private final BoundedBuffer<Integer> bb;
        private final int numTries;
        private final AtomicInteger sum;
        private final CyclicBarrier barrier;

        public Producer(BoundedBuffer<Integer> bb, int numTries, AtomicInteger putSum, CyclicBarrier barrier) {
            this.bb = bb;
            this.numTries = numTries;
            this.sum = putSum;
            this.barrier = barrier;
        }

        static int xorShift(int y) {
            y ^= (y << 6);
            y ^= (y >>> 21);
            y ^= (y << 7);
            return y;
        }

        @Override
        public void run() {
            try {
                int seed = (this.hashCode() ^ (int) System.nanoTime());
                int sum = 0;
                System.out.println(Thread.currentThread().getName()+" producer waiting to start");
                barrier.await();
                for (int i = 0; i < numTries; i++) {
                    bb.addItem(seed);
                    sum += seed;
                    seed = xorShift(seed);
                }
                this.sum.getAndAdd(sum);
                barrier.await();
                System.out.println(Thread.currentThread().getName()+" producer finished");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class Consumer implements Runnable {

        private final int numTries;
        private final AtomicInteger sum;
        private final BoundedBuffer<Integer> bb;
        private final CyclicBarrier barrier;

        public Consumer(BoundedBuffer<Integer> bb, int numTries, AtomicInteger getSum, CyclicBarrier barrier) {
            this.bb = bb;
            this.numTries = numTries;
            this.sum = getSum;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+" consumer waiting to start");

                barrier.await();
                int sum = 0;
                for (int i = 0; i < numTries; i++) {
                    Integer item = bb.getItem();
                    sum += item;
                }
                this.sum.getAndAdd(sum);
                barrier.await();
                System.out.println(Thread.currentThread().getName()+" consumer finished");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
