package epp.parallelcomputing;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentDictionaryWithCaching {

    private String lastInput;
    private List<String> lastResult;

    public List<String> getWords(String inputString) throws InterruptedException {
        synchronized (this) {
            if (inputString.equals(lastInput)) {
                return lastResult;
            }
        }

        List<String> result = computeWords(inputString);
        update(inputString, result);

        return result;


    }

    private synchronized void update(String inputString, List<String> result) {
        this.lastInput = inputString;
        this.lastResult = result;
    }

    private List<String> computeWords(String input) throws InterruptedException {

        ArrayList<String> result = new ArrayList<>();
        result.add(input);
        Thread.sleep(1000);
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrentDictionaryWithCaching dictionary = new ConcurrentDictionaryWithCaching();
        Runnable runners = () -> {
            try {
                for (int i=1;i<=100;i++) {
                    String input = Thread.currentThread().getName()+"_"+ i+"_" + System.nanoTime();
                    System.out.println("start "+input);
                    List<String> strings = dictionary.computeWords(input);
                    assert strings.contains(input);
                    Thread.sleep(10);
                    System.out.println("done "+input);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Thread t1 = new Thread(runners);
        Thread t2 = new Thread(runners);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }
}
