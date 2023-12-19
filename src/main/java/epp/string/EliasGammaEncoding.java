package epp.string;

import java.util.ArrayList;
import java.util.List;

public class EliasGammaEncoding {
    public static void main(String[] args) {
        int[] array = new int[]{13,25,17};
        String encoding = eliasGammaEncoding(array);
        System.out.println(encoding);
        List<Integer> decoded = eliasGammaDecoding(encoding);
        System.out.println(decoded);
    }

    public static List<Integer> eliasGammaDecoding(String encoding) {
        List<Integer> values = new ArrayList<>();
        while (encoding.length()>0){
            int i=0;
            while (encoding.charAt(i)=='0'){
                i++;
            }
            int value =Integer.parseInt( encoding.substring(i,2*i+1),2);
            values.add(value);
            encoding = encoding.substring(2*i+1);
        }
        return values ;
    }


    public static String eliasGammaEncoding(int[] array) {

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<array.length;i++){
            sb.append(eliasGammaEncoding(array[i]));
        }
        return sb.toString();
    }

    private static String eliasGammaEncoding(int number) {
        StringBuilder sb = new StringBuilder();
        String binString = Integer.toBinaryString(number);
        int zeros = binString.length()-1;
        for(int i=0;i<zeros;i++){
            sb.append('0');
        }
        sb.append(binString);

        return sb.toString();
    }
}
