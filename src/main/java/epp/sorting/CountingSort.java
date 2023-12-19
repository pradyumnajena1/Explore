package epp.sorting;

import epp.array.ArrayUtils;
import epp.hashmap.MapUtils;

import java.util.Map;

public class CountingSort {

    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(30,2,10);
        ArrayUtils.printArray(values);
        //bring equal values close to each other
        //complexity on(n) aditional space o(m) , where m unique values
        sortValues(values);
        ArrayUtils.printArray(values);
    }

    private static void sortValues(int[] values) {
        Map<Integer, Integer> frequencyMap = MapUtils.getFrequencyMap(values);
        int writePos = 0;
        for(Map.Entry<Integer,Integer> entry:frequencyMap.entrySet()){
            for(int i=0;i<entry.getValue();i++){
                values[writePos++] = entry.getKey();
            }
        }
    }
}
