package epp.string.revision;

import java.util.ArrayList;
import java.util.List;

public class ComputeAllValidIPAddresses {
    public static void main(String[] args) {
        String s = "19216811";
        List<String> ips = getAllValidIPAddresses(s);
        System.out.println(ips);
    }

    private static List<String> getAllValidIPAddresses(String s) {
        return getAllValidIPAddresses(s,4);
    }

    private static List<String> getAllValidIPAddresses(String s, int numParts) {
        if(s.isEmpty()){
            return List.of();
        }
        if(numParts==1 ){
           if(isValidPart(s)){

               return List.of(s);
           }else{
               return List.of();
           }
        }
        List<String> result = new ArrayList<>();
        for(int i=1;i<=Math.min( 3,s.length());i++){
            String prefix = s.substring(0,i);
            if(isValidPart(prefix)){
                String suffix = s.substring(i);
                List<String> validParts = getAllValidIPAddresses(suffix, numParts - 1);
                for(String validSuffix:validParts){
                    result.add(prefix+"."+validSuffix);
                }
            }
        }
        return result;
    }

    private static boolean isValidPart(String s) {
        return Integer.parseInt(s) > 0 && Integer.parseInt(s) < 256;
    }
}
