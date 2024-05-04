package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestTaskExecutor {
    private ExecutorService executorService = Executors.newFixedThreadPool(3);
    private CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
    public List<Integer> executeQueries(List<String> queries) throws ExecutionException, InterruptedException {
        List<Future<Integer>> futures = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
         for(String query:queries){
             QueryTask queryTask = new QueryTask(query);
             Future<Integer> future = executorService.submit(queryTask);
             futures.add(future);
         }
         for(Future<Integer> future:futures){
             Integer value = future.get();
             values.add(value);
         }
         return values;
    }

    public List<Integer> executeQueries2(List<String> queries) throws ExecutionException, InterruptedException {

        List<Integer> values = new ArrayList<>();
        for(String query:queries){
            QueryTask queryTask = new QueryTask(query);
              completionService.submit(queryTask);

        }
        for(int i=0;i< queries.size();i++){
            Integer value = completionService.take().get();
            values.add(value);
        }
          return values;
    }


    private static class QueryTask implements Callable<Integer> {
        private final String query;

        public QueryTask(String query){
            this.query=query;
        }
        @Override
        public Integer call() throws Exception {
            System.out.println("executed query "+query);
            return Integer.valueOf((int) (Math.random()*10));
        }
    }
}
