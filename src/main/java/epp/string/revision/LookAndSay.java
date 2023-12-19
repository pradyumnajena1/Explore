package epp.string.revision;

public class LookAndSay {
    public static void main(String[] args) {
        String s = lookAndSay(8);
        System.out.println(s);
    }

    private static String lookAndSay(int n) {
        if(n==1){
            return "1";
        }
        String previous = lookAndSay(n-1);
        String next ="";
        char prevDigit = previous.charAt(0);
        int count =1;
        for(int i=1;i<previous.length();i++){
            char currentDigit = previous.charAt(i);
            if(prevDigit== currentDigit){
                count++;
            }else {
                next=next+count+prevDigit;

                prevDigit= currentDigit;
                count=1;
            }
        }
        next=next+count+prevDigit;
        return next;
    }
}
