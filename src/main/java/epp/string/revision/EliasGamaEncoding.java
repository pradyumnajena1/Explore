package epp.string.revision;

import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class EliasGamaEncoding {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10,5,30);
        ArrayUtils.printArray(values);
        String egEncoding = eliasGammaEncoding(values);
        System.out.println(egEncoding);
        int[] decodedValues = eliasGammaDecoding(egEncoding);
        ArrayUtils.printArray(decodedValues);
    }

    private static int[] eliasGammaDecoding(String egEncoding) {
        List<Integer> result = new ArrayList<>();
        int i=0;
        while (i<egEncoding.length()){
            int current =i;
            while (egEncoding.charAt(current)=='0'){
                current++;
            }
            int numBits = current-i+1;
            int value = Integer.parseInt( egEncoding.substring(current,current+numBits),2);
            i=current+numBits;
            result.add(value);
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private static String eliasGammaEncoding(int[] values) {
        StringBuilder sb = new StringBuilder();
        for(int value:values){
            sb.append(eliasGammaEncoding(value));
        }
        return sb.toString();
    }

    private static String eliasGammaEncoding(int value) {
        StringBuilder sb = new StringBuilder();
        String binaryString = Integer.toBinaryString(value);
        for(int i=0;i<binaryString.length()-1;i++){
            sb.append('0');
        }
        sb.append(binaryString);


        return sb.toString();
    }
}
