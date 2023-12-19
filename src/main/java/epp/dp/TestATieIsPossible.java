package epp.dp;

import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TestATieIsPossible {

    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10,1,20);
        ArrayUtils.printArray(values);
        boolean test = testIfTiePossible(values);
        System.out.println(test);
    }

    private static boolean testIfTiePossible(int[] values) {
        int sum = Arrays.stream(values).reduce(0, (a, b) -> a + b);
        if(sum%2==1 || values.length<2){
            System.out.println("sum is odd");
            return false;
        }
        Map<Key,Boolean> cache = new HashMap<>();

        return testIfTiePossible(values,0,values.length-1,sum/2,sum/2,cache);
    }
    private static class Key{
        int start,end;
        int left;
        int right;

        public Key(int start, int end, int left, int right) {
            this.start = start;
            this.end = end;
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return start == key.start && end == key.end && left == key.left && right == key.right;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end, left, right);
        }
    }

    private static boolean testIfTiePossible(int[] values, int start,int end, int left, int right,Map<Key,Boolean> cache) {
        if(start+1==end){
            if(values[start]==left && values[end]==right || values[start]==right && values[end]==left ){
                return true;
            }
            return false;
        }
        Key key = new Key(start,end,left,right);


        boolean result =
                testIfTiePossible(values, start + 1, end, left - values[start], right,cache) || testIfTiePossible(values,
                        start + 1, end, left, right - values[start],cache);
        cache.put(key,result);

        return result;
    }
}
