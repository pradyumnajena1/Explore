package epp.parallelcomputing;

import java.util.concurrent.*;

public class Requester {

    private String requestString;

    public Requester(String requestString) {
        this.requestString = requestString;
    }

    public String execute(String request){
        try {
            Thread.sleep(1000 + (long) (Math.random()*2500));
            return request+" done";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
    public void dispatch()    {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(() -> execute(requestString));
        try {
            String s = future.get(3, TimeUnit.SECONDS);
            processResponse(s);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            error(e.toString());
        } catch (TimeoutException e) {
            error(e.toString());
        }
        executorService.shutdown();

    }

    public void processResponse(String response){
        System.out.println(response);
    }
    public void error(String error){
        System.out.println(error);
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){

            Requester requester= new Requester("hello "+System.nanoTime());
            requester.dispatch();
        }
    }
}
