package epp.array.revision;

import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class OnlineSample {
    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>();
        for(int i=0;i<30;i++){
            values.add(i+1);
        }
        int[] sample = getOnlineSample(values,10);
        ArrayUtils.printArray(sample);
    }

    private static int[] getOnlineSample(List<Integer> values, int numElements) {
        int[] result = new int[numElements];
        for(int i=0;i<numElements;i++){
            result[i] = values.get(i);
        }

        for(int i=numElements;i<values.size();i++){
          int target = (int) (Math.random()*(i));
          if(target<(numElements)){
                result[target] = values.get(i);
          }
        }

        return result;
    }
}
