package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.function.ToIntBiFunction;

public class SquareRootTechniqueMinQueryProcessor {

    public static void main(String[] args) {
        int[] randomValues = ArrayUtils.randomArray(12, 1, 20);
        SquareRootTechniqueMinQueryProcessor processor = new SquareRootTechniqueMinQueryProcessor(randomValues,Integer::min,Integer.MAX_VALUE);
        System.out.println(processor.getValue());
        processor.update(7,-21);
        System.out.println(processor.getValue());
    }


    private int[] values;
    ToIntBiFunction<Integer, Integer> function;
    int identity;
    Range[] ranges;

    public SquareRootTechniqueMinQueryProcessor(int[] values, ToIntBiFunction<Integer, Integer> function, int identity) {

        this.function = function;
        this.identity = identity;
        int sqrt = (int) Math.ceil(Math.sqrt(values.length));
        this.values = Arrays.copyOf(values, sqrt * sqrt);
        Arrays.fill(this.values,values.length,this.values.length,identity);
        ArrayUtils.printArray(this.values);
        ranges = new Range[sqrt];
        for(int i=0;i<ranges.length;i++){
            ranges[i] = new Range(this.values,i*sqrt,(i+1)*sqrt-1,function,identity);
        }
    }

    public int getValue() {
        int result = identity;
        for(int i=0;i<ranges.length;i++){
            result = function.applyAsInt(result,ranges[i].getValue());
        }
        return result;
    }

    public void update(int index, int delta) {
        int low = 0;
        int high = ranges.length-1;
        while (low<=high){
            int mid= (low+high)/2;
            if(ranges[mid].start<=index && index<=ranges[mid].end){
                ranges[mid].update(index,delta);
                break;
            } else if (ranges[mid].start>index) {
                high = mid-1;
            }else {
                low=mid+1;
            }
        }

    }



    private static class Range {
        int[] values;
        int start;

        int end;
        ToIntBiFunction<Integer, Integer> function;
        int identity;
        int result;

        public Range(int[] values, int start, int end, ToIntBiFunction<Integer, Integer> function, int identity) {
            this.values = values;
            this.start = start;
            this.end = end;
            this.function = function;
            this.identity = identity;
            this.result = doGetValue();
        }

        public int getValue() {
            return result;
        }

        public void update(int index, int delta) {
            values[index] += delta;
            result = doGetValue();
        }

        private int doGetValue() {
            int result = identity;
            for(int i=start;i<=end;i++){
                result = function.applyAsInt(result,values[i]);
            }
            return result;
        }
    }
}
