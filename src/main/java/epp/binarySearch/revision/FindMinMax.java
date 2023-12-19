package epp.binarySearch.revision;

import epp.array.ArrayUtils;

public class FindMinMax {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10,1,30);
        ArrayUtils.printArray(values);
        MinMax minMax = findMinMax(values);
        System.out.println(minMax);
    }

    private static MinMax findMinMax(int[] values) {
         if(values.length==1){
             return new MinMax(values[0],values[0]);
         }
         MinMax result = new MinMax(values[0],values[1]);
         // 3 compare for processing 2 elements, total (n/2)*3 = 3n/2
         for(int i=2;i+1<values.length;i+=2){
             MinMax local = new MinMax(values[i],values[i+1]);
             result.merge(local);
         }
         if(values.length%2==1){
             result.merge(new MinMax(values[values.length-1],values[values.length-1]));
         }
         return result;
    }

    private static class MinMax {
        int min;
        int max;

        public MinMax(int a, int b) {
           if(a<b){
               min=a;
               max=b;
           }else{
               min=b;
               max=a;
           }
        }
        public void merge(MinMax other){
            max = Math.max(this.max,other.max);
            min = Math.min(this.min,other.min);
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Result{");
            sb.append("min=").append(min);
            sb.append(", max=").append(max);
            sb.append('}');
            return sb.toString();
        }
    }
}
