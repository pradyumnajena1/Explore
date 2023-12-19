package epp.string.revision;

public class RunLengthEncode {
    public static void main(String[] args) {
        String s = "aaaabbbcca";
        String runLengthEncoding = runLengthEncoding(s);
        System.out.println(runLengthEncoding);
        String decoded = runLengthDecoding(runLengthEncoding);
        assert s.equals(decoded);
        System.out.println(decoded);

    }

    private static String runLengthDecoding(String runLengthEncoding) {
        if(runLengthEncoding==null||runLengthEncoding.isEmpty() ){
            throw new IllegalArgumentException("Invalid encoding");
        }
        StringBuilder sb = new StringBuilder();
        while (!runLengthEncoding.isEmpty()){
            char ch = runLengthEncoding.charAt(0);
            int index = 1;
            while (index<runLengthEncoding.length() && Character.isDigit( runLengthEncoding.charAt(index))){
                index++;
            }
            int count = Integer.parseInt(runLengthEncoding.substring(1,index));
            for(int i=0;i<count;i++){
                sb.append(ch);
            }
            runLengthEncoding = runLengthEncoding.substring(index);
        }

        return sb.toString();
    }

    private static String runLengthEncoding(String s) {
        StringBuilder sb = new StringBuilder();
        char lastChar = s.charAt(0);
        int lastCharCount = 1;
        for(int i=1;i<s.length();i++){
            char current = s.charAt(i);
            if(current ==lastChar){
                lastCharCount++;
            }else{
                sb.append(lastChar).append(lastCharCount);
                lastCharCount=1;
                lastChar= current;
            }
        }
        sb.append(lastChar).append(lastCharCount);
        return sb.toString();
    }
}
