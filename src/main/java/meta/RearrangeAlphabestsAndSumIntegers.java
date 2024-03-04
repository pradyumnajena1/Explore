package meta;

public class RearrangeAlphabestsAndSumIntegers {
    public static void main(String[] args) {
        String s = "AC2BEW3";
        String result = sortAlphaAndSumDigits(s);
        System.out.println(result);
    }

    private static String sortAlphaAndSumDigits(String s) {
        int MAXCHAR = 26;
        int[] freq = new int[MAXCHAR];
        int sum = 0;
        for(int i=0;i<s.length();i++){
            if(Character.isDigit( s.charAt(i))){
                sum+= s.charAt(i)-'0';
            }else{
                freq[s.charAt(i)-'A']++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<MAXCHAR;i++){
            while (freq[i]>0){
                char ch = (char) ('A' + i);
                sb.append(ch);
                freq[i]--;
            }
        }
        sb.append(sum);
        return sb.toString();
    }
}
