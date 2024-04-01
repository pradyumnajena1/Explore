package concurrency;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DelegatingVehicleTracker {


    public static void main(String[] args) throws InterruptedException {
        HashMap<String, Point> points = new HashMap<>();
        points.put("1",new Point(0,0));
        points.put("2",new Point(0,0));
        points.put("3",new Point(0,0));
        points.put("4",new Point(0,0));
        DelegatingVehicleTracker tracker = new DelegatingVehicleTracker(points);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable updater = () -> {
            for (int i = 0; i < 20; i++) {
                int id = 1 + (int) (Math.random() * 4);
                int x = 1 + (int) (Math.random() * 4);
                int y = 1 + (int) (Math.random() * 4);
                tracker.setLocation(String.valueOf(id), x, y);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Runnable reader = () -> {
            for (int i=0;i<10;i++) {
                Map<String, Point> locations1 = tracker.getLocations();
                for (String key : locations1.keySet()) {
                    System.out.println(key + locations1.get(key));
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        executorService.submit(updater);
        executorService.submit(reader);
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }

    private ConcurrentHashMap<String,Point> locations;
    private Map<String,Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String,Point> points) {
        locations = new ConcurrentHashMap<>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Point getLocation(String id){
        return locations.get(id);
    }
    public Map<String,Point> getLocations(){
        return unmodifiableMap;
    }
    public void setLocation(String id,int x,int y){
        Point point = new Point(x,y);
        if(locations.get(id )==null){
            throw new IllegalArgumentException("Invalid id "+id);
        }
        locations.replace(id,point);
    }

    private static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Point.class.getSimpleName() + "[", "]")
                    .add("x=" + x)
                    .add("y=" + y)
                    .toString();
        }
    }
}
