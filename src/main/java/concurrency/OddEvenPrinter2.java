package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class OddEvenPrinter2 {

    private static class Syncronizer{
        private Semaphore oddTurn;
        private Semaphore evenTurn;

        public Syncronizer(boolean turn) {
            oddTurn = new Semaphore(turn?1:0);
            evenTurn = new Semaphore(turn?0:1);
        }

        public void acquireTurn(boolean turn) throws InterruptedException {
            if(turn){
                oddTurn.acquire();
            }else{
                evenTurn.acquire();
            }
        }
        public void releaseTurn(boolean turn) throws InterruptedException {
            if(turn){
                oddTurn.release();
            }else{
                evenTurn.release();
            }
        }
    }

}
