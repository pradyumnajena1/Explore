package epp.binarysearchtree.revision;

import epp.array.ArrayUtils;
import epp.binarySearch.revision.FindTheKthElement;

import java.util.*;
import java.util.stream.Collectors;

public class MostVisitedPageTrackerCalledOnlyOnce {
    public static void main(String[] args) {
        MostVisitedPageTrackerCalledOnlyOnce mostVisitedPageTracker = new MostVisitedPageTrackerCalledOnlyOnce();
        String[] uniquePages = {"hello5","hello4", "hello3",   "hello", "hello2", "hello1"};
        for (int i = 0; i < 50; i++) {
            String uniquePage = uniquePages[(int) (Math.random() * uniquePages.length)];
            System.out.println(uniquePage);
            mostVisitedPageTracker.addPage(uniquePage, System.currentTimeMillis());

        }
        System.out.println(mostVisitedPageTracker.countMap);

        List<String> mostVisitedPages = mostVisitedPageTracker. getMostFrequentPages(3);
        System.out.println(mostVisitedPages);


    }

    private Map<String,Integer> countMap;

    public MostVisitedPageTrackerCalledOnlyOnce() {
        countMap = new HashMap<>();
    }

    public void addPage(String page, Long timestamp){
        Integer count = countMap.getOrDefault(page, 0);
        countMap.put(page,count+1);
    }
    public List<String> getMostFrequentPages(int max){
        String[] array =  countMap.keySet().stream().toArray(String[]::new) ;
        List<String> pages =  findLargestItems(array,max);
        return pages;
    }

    private List<String> findLargestItems(String[] array, int max) {
        return findLargestItems(array,0,array.length-1,max);
    }

    private List<String> findLargestItems(String[] array, int start, int end, int k) {
        Comparator<String> comparator = Comparator.comparingInt((String x) -> countMap.get(x)).reversed();

        int index = 0;
        while (start<=end){
            index = FindTheKthElement.partition(array,start,end,
                    comparator);
           if(index-start==k-1){
               break;
           } else if (index-start<k-1) {
               start = index+1;
           }else{
               end = index-1;
           }
         }
        List<String> collect = Arrays.stream(array, start, index + 1).collect(Collectors.toList());
        return collect;
    }


}
