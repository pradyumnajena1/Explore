package epp;

import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.IntFunction;
import java.util.stream.Stream;

public class ListUtils {

    public static void main(String[] args) {
        Stream<Optional<String>> stream = Stream.of("A", "AAA", "B", "AAB", "C")
                .filter(string -> string.startsWith("A"))
                .map(Optional::of);
        Optional<String>[] strings = stream
                .toArray( genericArray( Optional[]::new));
        List<List<Number>> lists = List.of(List.of(1),List.of(2),List.of(3));
        List<Integer>[] array=  lists.stream().toArray(genericArray(List[]::new));
        ArrayUtils.printArray(array);

        List<Number> values = List.of(1, 2, 3);
        Integer[] array1 = convertToArray(values,Integer[]::new);
        ArrayUtils.printArray(array1);
    }
    public static <T ,R extends T>  R[] convertToArray(List<T> values,IntFunction<T[]> arrayCreator){
        IntFunction<R[]> generator = genericArray(arrayCreator);
        return values.stream().toArray(generator);
    }

    @SuppressWarnings("unchecked")
    static <T, R extends T> IntFunction<R[]> genericArray(IntFunction<T[]> arrayCreator) {
        return size -> (R[]) arrayCreator.apply(size);
    }
    public static<T> void swap(List<T> values,int i,int j){
        T temp = values.get(i);
        values.set(i,values.get(j));
        values.set(j,temp);
    }



    public static List<Long> cumulativeSum(List<Integer> values ){
        List<Long> sum = new ArrayList<>();
        sum.add(Long.valueOf(values.get(0)));
        for(int i=1;i<values.size();i++){
            sum.add(sum.get(i-1)+values.get(i));
        }
        return sum;
    }

    public static<T> void reverse(List<T> list){
        int start = 0;
        int end = list.size()-1;
        while (start<end){
            T copy = list.get(start);
            list.set(start,list.get(end));
            list.set(end,copy);
           start++;
           end--;
        }

    }

    public static int getFirstIndex(List<Integer> list,int value){
        return getFirstIndex(list,0,list.size()-1,value);
    }

    public static int getLastIndex(List<Integer> list,int value){
        return getLastIndex(list,0,list.size()-1,value);
    }

    public static int getFirstIndex(List<Integer> list, int start, int end, int value) {
        int index = -1;
        while (start<=end){
            int mid = (start+end)/2;
            if(list.get(mid)==value){
                index = mid;
                end=mid-1;
            }else if(list.get(mid)>value){
                end=mid-1;
            }else{
                start=mid+1;
            }
        }
        return index;
    }

    public static int getLastIndex(List<Integer> list, int start, int end, int value) {
        int index = -1;
        while (start<=end){
            int mid = (start+end)/2;
            if(list.get(mid)==value){
                index = mid;
                start=mid+1;
            }else if(list.get(mid)>value){
                end=mid-1;
            }else{
                start=mid+1;
            }
        }
        return index;
    }


}
