package meta;

import java.util.stream.Collectors;

public class CalculateMaxValueUsingPlusOrMult {
    public static void main(String[] args) {
        String s = "3136";
        int largestValue = generateLargestValue(s);
        System.out.println(largestValue);
    }

    private static int generateLargestValue(String s) {
        return generateLargestValue(s,0,s.length()-1);
    }

    private static int generateLargestValue(String s, int start, int end) {

        if(start==end){
            return Integer.parseInt(s.substring(start,end+1));
        }
        int largest = 0;
        for(int i=start;i<end;i++){
            int left = generateLargestValue(s,start,i);
            int right = generateLargestValue(s,i+1,end);
            largest = Math.max(largest,  Math.max(left+right,left*right));
        }
        return largest;
    }
}
