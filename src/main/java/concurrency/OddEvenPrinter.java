package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OddEvenPrinter {
    public static void main(String[] args) throws InterruptedException {
        Syncronizer syncronizer = new Syncronizer(Turn.Odd);
        OddPrinterTask oddPrinterTask = new OddPrinterTask(syncronizer);
        EvenPrinterTask evenPrinterTask = new EvenPrinterTask(syncronizer);
        ExecutorService executorService = Executors.newFixedThreadPool(2) ;
        executorService.submit(oddPrinterTask);
        executorService.submit(evenPrinterTask);
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }
    private static enum Turn{
        Odd,Even;
    }
    private static class Syncronizer{
        private Turn turn;

        public Syncronizer(Turn turn) {
            this.turn = turn;
        }

        public synchronized void awaitTurn(Turn turn) throws InterruptedException {
            while (this.turn!=turn){
                this.wait();
            }
        }

        public synchronized void releaseTurn() {
            turn = turn==Turn.Even?Turn.Odd:Turn.Even;
            this.notify();
        }


    }
    private static class OddPrinterTask implements Runnable{

        private Syncronizer syncronizer;

        public OddPrinterTask(Syncronizer syncronizer) {
            this.syncronizer = syncronizer;
        }

        @Override
        public void run() {
            for(int i=1;i<100;i+=2){
                try {
                    syncronizer.awaitTurn(Turn.Odd);
                    System.out.println(Thread.currentThread().getName()+" "+ i);
                    syncronizer.releaseTurn();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

        }
    }
    private static class EvenPrinterTask implements Runnable{

        private Syncronizer syncronizer;

        public EvenPrinterTask(Syncronizer syncronizer) {
            this.syncronizer = syncronizer;
        }

        @Override
        public void run() {
            for(int i=2;i<100;i+=2){
                try {
                    syncronizer.awaitTurn(Turn.Even);
                    System.out.println(Thread.currentThread().getName()+" "+i);
                    syncronizer.releaseTurn();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

        }
    }
}
