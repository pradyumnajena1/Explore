package epp.parallelcomputing;

public class InterLeavingThreads {

    private static class OddEvenLock{
        public static boolean ODD_TURN = true;
        public static boolean EVEN_TURN = true;
        private boolean turn;

        public OddEvenLock(boolean turn) {
            this.turn = turn;
        }
        public synchronized void waitForTurn(boolean turn)   {
            while (this.turn !=turn){
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }



        }

        public synchronized void  releaseTurn(){
            turn =!turn;
            notify();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        OddEvenLock lock = new OddEvenLock(true);
        Runnable oddThread = ()->{
            for(int i=1;i<=100;i+=2){

                    lock.waitForTurn(OddEvenLock.ODD_TURN);
                    System.out.println("odd Thread "+ i);
                    lock.releaseTurn();


            }

        };
        Runnable evenThread = ()->{
            for(int i=2;i<=100;i+=2){

                    lock.waitForTurn(OddEvenLock.EVEN_TURN);
                    System.out.println("even thread "+i);
                    lock.releaseTurn();



            }

        };
        Thread even  = new Thread(evenThread);
        Thread odd  = new Thread(oddThread);
        even.start();odd.start();
        even.join();odd.join();
    }
}
