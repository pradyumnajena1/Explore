package hackerrank.medium;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SuperDigit {
    public static void main(String[] args) throws IOException {
        System.out.println(superDigit("148", 3));
        System.out.println(superDigit("9875", 4));
        System.out.println(superDigit("9875", 4));
        System.out.println(superDigit(new String(Files.readAllBytes(Paths.get("C:\\Users\\Pradyumna\\IdeaProjects" +
                "\\Explore\\src\\main\\resources\\SuperDigitInput.txt" )), StandardCharsets.UTF_8), 1000));
    }

    public static int superDigit(String n, int k) {
        // Write your code here
        List<Integer> digits = getDigits(n);
        if(digits.size()==1){
            return digits.get(0);
        }
        int sum = digits.stream().mapToInt(Integer::intValue).sum();
        sum=sum*k;
        return superDigit(sum+"");
    }

    public static int superDigit(String n) {
        // Write your code here
        List<Integer> digits = getDigits(n);
        if(digits.size()==1){
            return digits.get(0);
        }
        int sum = digits.stream().mapToInt(Integer::intValue).sum();
        return superDigit(sum+"");
    }

    private static List<Integer> getDigits(String n) {
        List<Integer> list = new ArrayList<>();
        char[] charArray = n.toCharArray();
        for(int i=0;i<charArray.length;i++){
            list.add(charArray[i]-'0');
        }
        return list;
    }


}
