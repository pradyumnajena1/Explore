package guidetocompetitiveprogramming;

import epp.Pair;
import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindSubStringUsingZArray {
    public static void main(String[] args) {
        List<Integer> indices = findSubString("ABCABABCA", "ABC");
        System.out.println(indices);
        System.out.println(findBorderStrings("ABACABACABA"));
    }

    private static List<Integer> findSubString(String s, String pattern) {
       int[] zArray = ZArray.computeZArray (pattern+"#"+s);
        ArrayUtils.printArray(zArray);
       return IntStream.range(0, zArray.length).filter(i -> zArray[i] == pattern.length()).map(i->i-pattern.length()-1) .boxed().collect(Collectors.toList());
    }

    private static List<String> findBorderStrings(String s ) {
        int[] zArray = ZArray.computeZArray ( s);
        ArrayUtils.printArray(zArray);
        return IntStream.range(0, zArray.length).filter(i ->i+ zArray[i] == s.length()).mapToObj(i->s.substring(i,i+zArray[i]) ).collect(Collectors.toList());
    }
}
