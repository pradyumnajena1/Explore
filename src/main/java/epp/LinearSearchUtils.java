package epp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LinearSearchUtils {

    public static void main(String[] args) {
        int[] values= {5,1};
        List<Integer> list = Arrays.stream(values).boxed().collect(Collectors.toList());
        System.out.println(list);
        int lastItemSmallerThan = findLastItemSmallerThan(list, 0, list.size() - 2, 1);
        System.out.println(lastItemSmallerThan);
    }
    public static <T extends Comparable<T>> int findFirstItemBiggerThan(List<T> list, int start, int end, T data) {
       int i=start;
        for( ;i<=end;i++){
           if(list.get(i).compareTo(data)>0){
               return i;
           }
       }
       return -(start+1);
    }
    public static <T extends Comparable<T>> int findLastItemSmallerThan(List<T> list, int start, int end, T data) {
        int i=end;
        for(;i>=start;i--){
            if(list.get(i).compareTo(data)<0){
                return i;
            }
        }
        return -(start+1);
    }
    public static <T extends Comparable<T>> int findLastItemSmallerThanEquals(List<T> list, int start, int end,
                                                                              T data) {
        int i=end;
        for(;i>=start;i--){
            if(list.get(i).compareTo(data)<=0){
                return i;
            }
        }
        return -(start+1);
    }
}
