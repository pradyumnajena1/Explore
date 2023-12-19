package epp.hashmap.revision;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * range [a,b] means containing a,a+1...b inclusive, all the intermidiate values should also be present;
 */
public class BiggestRangeContainedInArray {
    public static void main(String[] args) {
        int[] values = {3,-2,7,9,8,1,2,0,};
        Range range = findBiggestRangeContained(values);
        System.out.println(range);
    }

    private static Range findBiggestRangeContained(int[] values) {
        Set<Integer> set = Arrays.stream(values).boxed().collect(Collectors.toSet());
        Range max= null;
        for(int i=0;i< values.length;i++){
            if(set.contains( values[i])){
                 int left = values[i];
                 while (set.contains(left)){
                     set.remove(left);
                     left--;
                 }
               int right = values[i]+1;
                while (set.contains(right)){
                    set.remove(right);
                    right++;
                }
                 Range range = new Range(left+1,right-1);
                 if(max==null||max.dist()<range.dist()){
                     max=range;
                 }
            }
        }
        return max;
    }
}
