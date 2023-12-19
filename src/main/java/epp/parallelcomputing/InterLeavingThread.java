package epp.parallelcomputing;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InterLeavingThread {
    private Lock lock;
    private Condition okForEven;
    private Condition okForOdd;
    private boolean current;
    private static final boolean ODD_TURN =false;
    private static final boolean EVEN_TURN =true;

    public InterLeavingThread() {
        lock = new ReentrantLock();
        current = ODD_TURN;
        okForOdd = lock.newCondition();
        okForEven = lock.newCondition();
    }

    public void writeEven(int value){
        lock.lock();
          while (current!=EVEN_TURN){
              try {
                  okForEven.await();
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
          }

        lock.unlock();

        System.out.println(value);

        lock.lock();
        current = ODD_TURN;
        okForOdd.signal();
        lock.unlock();
    }

    public void writeOdd(int value){
        lock.lock();
        while (current!=ODD_TURN){
            try {
                okForOdd.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        lock.unlock();

        System.out.println(value);

        lock.lock();
        current = EVEN_TURN;
        okForEven.signal();
        lock.unlock();
    }

    public static void main(String[] args) {
        InterLeavingThread monitor = new InterLeavingThread();
        Thread even = new Thread(() -> {
            for(int i=2;i<100;i+=2){
                monitor.writeEven(i);
            }
        });
        Thread odd = new Thread(() -> {
            for(int i=1;i<100;i+=2){
                monitor.writeOdd(i);
            }
        });
        odd.start();
        even.start();

        try {
            odd.join();
            even.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
