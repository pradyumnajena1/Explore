package concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ListHelper<T> {
    public static void main(String[] args) throws InterruptedException {
        ListHelper<Integer> list = new ListHelper<>();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(()->{
            for(int i=0;i<10000;i++){
                list.putIfAbsent((int) (Math.random()*10));

            }
        });
        executorService.submit(()->{
            for(int i=0;i<1000;i++){
                list.add(i);
            }
        });
        executorService.submit(()->{
            for(int i=0;i<1000;i++){
                System.out.println(list.list);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
       executorService.awaitTermination(20, TimeUnit.SECONDS);
       executorService.shutdown();
    }
    private List<T> list;

    public ListHelper() {
        list = Collections.synchronizedList(new ArrayList<>());
    }
    public    void putIfAbsent(T value){
        synchronized (list){
            boolean absent = !list.contains(value);
            if(absent)list.add(value);
        }


    }
    public void add(T value){
        list.add(value);
    }
}
