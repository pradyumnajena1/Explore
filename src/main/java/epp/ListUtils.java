package epp;

import epp.array.ArrayUtils;

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

}
