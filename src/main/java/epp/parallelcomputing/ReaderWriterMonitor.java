package epp.parallelcomputing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReaderWriterMonitor {

     private Lock lock;
     private int activeReader = 0;
     private int waitingReader = 0;
     private int activeWriter = 0;
     private int waitingWriter = 0;

     private Condition okToRead;
     private Condition okToWrite;

    public ReaderWriterMonitor() {
        lock = new ReentrantLock();
        okToRead = lock.newCondition();
        okToWrite = lock.newCondition();
    }

    public void write(){
        lock.lock();
        while (activeReader +activeWriter>0){
            waitingWriter++;
            try {
                okToWrite.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            waitingWriter--;
        }
        activeWriter++;
        lock.unlock();

        doWrite();


        lock.lock();
        activeWriter--;
        if(waitingWriter>0){
            okToWrite.signal();
        }else if(waitingReader>0){
            okToRead.signalAll();
        }
        lock.unlock();
    }

    private static void doWrite() {
        System.out.println(Thread.currentThread().getName()+ "writer writing some lines");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+ "writer writing some more lines");
    }

    public void read(){
        lock.lock();
        while (activeWriter+waitingWriter>0){
            waitingReader++;
            try {
                okToRead.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            waitingReader--;
        }
        activeReader++;
        lock.unlock();


        doRead();

        lock.lock();
        activeReader--;
        if(activeReader==0 && waitingWriter>0){
            okToWrite.signal();
        }
        lock.unlock();

    }

    private static void doRead() {
        System.out.println(Thread.currentThread().getName()+ "reader reading some lines");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+ "reader reading some more lines");
    }

    public static void main(String[] args) throws InterruptedException {
        ReaderWriterMonitor monitor = new ReaderWriterMonitor();
        List<Thread> allThreads = new ArrayList<>();
        for (int t=0;t<3;t++) {
            int repeatations = 2;
            Thread reader = new Thread( ()-> {
                for (int i = 0; i< repeatations; i++) {

                    monitor.read();
                }
                System.out.println(Thread.currentThread().getName()+" done reading");
            },"Reader "+t);
            Thread writer = new Thread(()->{
                for (int i = 0; i< repeatations; i++) {
                    monitor.write();
                }
                System.out.println(Thread.currentThread().getName()+" done writing");
            },"writer "+t);
            reader.start();
            writer.start();
             allThreads.add(reader);
             allThreads.add(writer);
        }

        for(Thread t:allThreads){
            t.join();
        }
    }
}
