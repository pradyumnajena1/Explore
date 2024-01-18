package hackerrank.medium;

import guidetocompetitiveprogramming.GenericSegmentTree;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class AndXorOr {

    private static class MinMin{
        int min;
        int secondMin;
        int andOrXor;

        public MinMin(int min, int secondMin) {
            this.min = min;
            this.secondMin = secondMin;
            this.andOrXor =  getAndXorOr();
        }
        public MinMin(int min) {
            this.min = min;
            this.secondMin = Integer.MAX_VALUE;
            this.andOrXor =  0;
        }
        private     int getAndXorOr( ) {
            int or =  min |  secondMin;
            int and =  min &  secondMin;
            int xor =  min ^  secondMin;
            int s = (and ^ or) & xor;
            return s;
        }
        public static MinMin merge(MinMin a,MinMin b){
            MinMin merged = null;
            if(a.min<b.min){
                merged = new MinMin(a.min,Math.min(a.secondMin, b.min));
            }else{
                merged= new MinMin(b.min,Math.min(a.min, b.secondMin));
            }
            merged.andOrXor =Math.max(merged.andOrXor,  Math.max(a.andOrXor,b.andOrXor));
            return merged;

        }

        @Override
        public String toString() {
            return "MinMin{" +
                    "min=" + min +
                    ", secondMin=" + secondMin +
                    ", andOrXor=" + andOrXor +
                    '}';
        }

        public static void main(String[] args) {
            System.out.println(merge(new MinMin(9 ), new MinMin(6 )));
        }
    }

    public static int andXorOr(List<Integer> a) {
        // Write your code here
        List<MinMin> minMins = a.stream().map(MinMin::new).collect(toList());
        GenericSegmentTree<MinMin> segmentTree = new GenericSegmentTree<>(minMins,MinMin::merge,new MinMin(Integer.MAX_VALUE));
        segmentTree.printTree();

        int maxValue = Integer.MIN_VALUE;
        for(int i=0;i<a.size();i++){
            for(int j=i+1;j<a.size();j++){
                MinMin minMin = segmentTree.rangeResult(i, j);
                int s = minMin.andOrXor;
               // System.out.println(i +" " +j +" "+ minMin + " "+s);
                maxValue = Math.max(s,maxValue);
            }
        }
        return maxValue;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int aCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result =  andXorOr(a);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
