package concurrency;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.function.Function;

public class Cache<A, V> {
    private Function<A, V> function;
    private ConcurrentHashMap<A, FutureTask<V>> map;

    public Cache(Function<A, V> function) {
        this.function = function;
        map = new ConcurrentHashMap<>();
    }

    public static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException)
            return (RuntimeException) t;
        else if (t instanceof Error)
            throw (Error) t;
        else
            throw new IllegalStateException("Not unchecked", t);
    }

    public V compute(A arg) throws InterruptedException {
        while (true) {
            FutureTask<V> futureTask = map.get(arg);
            if (futureTask == null) {
                FutureTask<V> ft = new FutureTask<>(() -> function.apply(arg));
                futureTask = map.putIfAbsent(arg, ft);
                if (futureTask == null) {
                    futureTask = ft;
                    futureTask.run();
                }
            }
            try {
                return futureTask.get();
            } catch (CancellationException e) {
                map.remove(arg, futureTask);
            } catch (ExecutionException e) {
                throw launderThrowable(e.getCause());
            }
        }

    }
}
