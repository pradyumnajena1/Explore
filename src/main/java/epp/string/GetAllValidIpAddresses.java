package epp.string;

import java.util.ArrayList;
import java.util.List;

public class GetAllValidIpAddresses {
    public static void main(String[] args) {
        List<String> ip_addresses = getAllValidIpAddresses("19216811");
        System.out.println(ip_addresses);
    }

    private static List<String> getAllValidIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        for(int i=1;i<4 && i< s.length();i++){
            String firstPart = s.substring(0,i);
            if(isValidSegment(firstPart)){
                for(int j=1;j<4 && i+j < s.length();j++){
                    String secondPart = s.substring(i,i+j);
                    if(isValidSegment(secondPart)){
                        for(int k=1;k<4 && i+j+k < s.length();k++){
                            String thirdPart = s.substring(i+j,i+j+k);
                            String fourthpart = s.substring(i+j+k);
                            if(isValidSegment(thirdPart) && isValidSegment(fourthpart)){
                                result.add(firstPart+"."+secondPart+"."+thirdPart+"."+fourthpart);
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    private static boolean isValidSegment(String part) {
        if(part.length()<=0 || part.length()>=4){
            return false;
        }
        if(part.length()>1 && part.startsWith("0")){
            return false;
        }
        int value = Integer.parseInt(part);
        if(value>255 || value<0){
            return false;
        }
        return true;
    }
}
