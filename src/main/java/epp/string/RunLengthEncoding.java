package epp.string;

public class RunLengthEncoding {

    public static void main(String[] args) {
        String s = "aaaabcccaa";
        String encoded = runLengthEncode(s);
        System.out.println(encoded);
        String decode = decode(encoded);
        System.out.println(decode);
    }
    public static String decode(String encodedString){
        if(encodedString==null || encodedString.length()==0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (encodedString.length()>0){
            char ch = encodedString.charAt(0);
            int index =1;
            while (index<encodedString.length() && Character.isDigit( encodedString.charAt(index))){
                index++;
            }
            int count = Integer.parseInt(encodedString.substring(1,index));
            for(int i=0;i<count;i++){

                sb.append(ch);
            }

            encodedString = encodedString.substring(index);
        }
        return sb.toString();
    }

    public static String runLengthEncode(String s) {
        if(s==null ||s.length()==0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char lastChar = s.charAt(0);
        int count = 1;
        for(int i=1;i<s.length();i++){
            char currentChar = s.charAt(i);
            if(currentChar ==lastChar){
                count++;
            }else {
                sb.append(lastChar);
                sb.append(count);
                lastChar = currentChar;
                count=1;
            }
        }
        sb.append(lastChar);
        sb.append(count);
        return sb.toString();
    }
}
