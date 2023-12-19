package epp.binarySearch.revision;

import epp.array.ArrayUtils;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class FindTheKthLargestElementInAStream {
    public static void main(String[] args) {

        Stream<Integer> stream = Stream.generate(() -> (int) (Math.random() * 100)).limit(100);
        int max_k = getKthMax(stream,5);
        System.out.println(max_k);

    }

    private static int getKthMax(Stream<Integer> stream,int k) {
        int[] values = new int[2*k-1];
        AtomicInteger writePosition = new AtomicInteger();
        stream.forEach((Integer x)->{
            values[writePosition.getAndIncrement()] = x;
            if(writePosition.get() == 2*k-1){
                FindTheKthElement.findKthItem(values,k,0,2*k-2, Comparator.reverseOrder());
                writePosition.set(k);
            }
        });
        ArrayUtils.printArray(values);
        FindTheKthElement.findKthItem(values,k,0,2*k-2, Comparator.reverseOrder());
        ArrayUtils.printArray(values);
        return values[k-1];
    }
}
