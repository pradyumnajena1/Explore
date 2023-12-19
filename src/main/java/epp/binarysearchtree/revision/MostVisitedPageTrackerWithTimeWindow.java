package epp.binarysearchtree.revision;

import epp.Pair;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class MostVisitedPageTrackerWithTimeWindow extends MostVisitedPageTracker{
    public static void main(String[] args) throws InterruptedException {
        MostVisitedPageTrackerWithTimeWindow mostVisitedPageTracker = new MostVisitedPageTrackerWithTimeWindow(100);
        String[] uniquePages = {"hello5","hello4", "hello3",   "hello", "hello2", "hello1"};
        for (int i = 0; i < 10; i++) {
            String uniquePage = uniquePages[(int) (Math.random() * uniquePages.length)];

            long timeStamp = System.currentTimeMillis();
            System.out.println(uniquePage+" "+timeStamp);
            mostVisitedPageTracker.addPage(uniquePage, timeStamp);
            Thread.sleep((long) (50*Math.random()+1));
        }

        List<String> mostVisitedPages = mostVisitedPageTracker.mostVisitedPages(3);
        System.out.println(mostVisitedPages);
        System.out.println(mostVisitedPageTracker.pageCount);
    }

    private Queue<Pair<String,Long>> queue;
   private int window;

    public MostVisitedPageTrackerWithTimeWindow(int window) {
        queue = new ArrayDeque<>();
        this.window=window;
    }

    @Override
    public void addPage(String page, long timeStamp) {
        super.addPage(page, timeStamp);
        queue.offer(new Pair<>(page,timeStamp));
        while (timeStamp- queue.peek().getSecond() >  window){
            Pair<String, Long> poll = queue.poll();
            decrementPageCount(poll.getFirst());
        }

    }
}
