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
            this.andOrXor = this.secondMin==Integer.MAX_VALUE?0:  andXorOr();
        }
        public MinMin(int min) {
            this.min = min;
            this.secondMin = min;
            this.andOrXor = 0;
        }

        public int getAndOrXor() {
            return andOrXor;
        }

        private     int andXorOr( ) {
            int or =  min |  secondMin;
            int and =  min &  secondMin;
            int xor =  min ^  secondMin;
            int s = (and ^ or) & xor;
            return s;
        }
        public static MinMin merge(MinMin a,MinMin b){
            MinMin merged = null;
            if(a.min <b.min){
                merged = new MinMin(a.min, a.min==a.secondMin ? b.min :  Math.min(a.secondMin, b.min));
            }else{
                merged= new MinMin(b.min,  b.min==b.secondMin ? a.min :   Math.min(a.min, b.secondMin));
            }
            MinMin result = merged;

            if(a.andOrXor>result.andOrXor ){
                result.andOrXor = a.andOrXor;
            }
            if(b.andOrXor>result.andOrXor ){
                result.andOrXor = b.andOrXor;
            }



            return result;

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
        GenericSegmentTree<MinMin> segmentTree = new GenericSegmentTree<>(minMins,MinMin::merge,
                new MinMin(Integer.MAX_VALUE ));
        segmentTree.printTree();

        int maxValue = segmentTree.rangeResult(0,a.size()-1).getAndOrXor() ;
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
