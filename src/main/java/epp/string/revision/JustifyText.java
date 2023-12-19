package epp.string.revision;

import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class JustifyText {
    public static void main(String[] args) {
        String[] text = new String[]{"The","quick","brown","fox","jumped","over","the","lazy","dogs."};
        String[] lines = justifyText(text,11);
        ArrayUtils.printArray(lines);
    }

    private static String[] justifyText(String[] text, int lineLength) {
        List<String> result = new ArrayList<>();
        List<String> currentLine = new ArrayList<>();
        int wordLength = 0;
        for(int i=0;i<text.length;i++){
            String word = text[i];
            if(wordLength+word.length()+ currentLine.size()-1<=lineLength){
                currentLine.add(word);
                wordLength+=word.length();
            }else{
              String adjustedLine =  adjustLine(currentLine,wordLength,
                      lineLength);
              result.add(adjustedLine);
                currentLine=new ArrayList<>();
                wordLength = word.length();
                currentLine.add(word);
            }
        }
        result.add(adjustLastLine(currentLine,wordLength,lineLength));
        return result.toArray(new String[0]);
    }

    private static String adjustLine(List<String> currentLine, int wordLength, int requiredLength) {
        List<String> spaces = new ArrayList<>();
        int totalLength = wordLength;
        String space = "_";
        for(int i = 0; i<currentLine.size()-1; i++){
            spaces.add(space);
            totalLength++;
        }
        int index = 0;
        while (totalLength<requiredLength){
            spaces.set(index % spaces.size(),  spaces.get(index % spaces.size())+ space);
            index++;
            totalLength++;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(currentLine.get(0));
        for(int i=1;i< currentLine.size();i++){
            sb.append(spaces.get(i-1));
            sb.append(currentLine.get(i));
        }

        return sb.toString();
    }

    private static String adjustLastLine(List<String> currentLine, int wordLength, int requiredLength) {
        List<String> spaces = new ArrayList<>();
        int totalLength = wordLength;
        String space = "_";
        for(int i = 0; i<currentLine.size()-1; i++){
            spaces.add(space);
            totalLength++;
        }
        if(totalLength<requiredLength){
            String lastSpace = "";
            while (totalLength<requiredLength){
                lastSpace+= space;
                totalLength++;
            }
            spaces.add(lastSpace );
        }
        StringBuilder sb = new StringBuilder();
        sb.append(currentLine.get(0));
        for(int i=1;i< currentLine.size();i++){
            sb.append(spaces.get(i-1));
            sb.append(currentLine.get(i));
        }
        sb.append(spaces.get(spaces.size()-1));

        return sb.toString();
    }
}
