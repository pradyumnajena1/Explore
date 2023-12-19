package epp.string;

import java.util.ArrayList;
import java.util.List;

public class JustifyText {
    public static void main(String[] args) {
        List<List<String>> justifyText = justifyText(new String[]{"The","quick","brown","fox","jumped","over","the",
                        "lazy","dogs"}
                ,11);
        System.out.println(justifyText);

        justifyText = justifyText(new String[]{ "a","ab","abc","abcd","abcdefghijkl"}
                ,17);
        System.out.println(justifyText);
    }

    public static List<List<String>> justifyText(String[] strings, int maxLineLength) {
        List<List<String>> unjustifiedLines = new ArrayList<>();
        List<String> currentLine = new ArrayList<>();
        for(int i=0;i<strings.length;i++){
            String currentWord= strings[i];
            if(getLineLength(currentLine)+currentWord.length()+1<=maxLineLength){
                currentLine.add(currentWord);
            }else{
                unjustifiedLines.add(currentLine);
                currentLine = new ArrayList<>();
                currentLine.add(currentWord);
            }


        }
       if(currentLine.size()>0){

           unjustifiedLines.add(currentLine);
       }
       List<List<String>> justifiedLines = new ArrayList<>();
       for(int i=0;i<unjustifiedLines.size();i++){

           justifiedLines.add(   justifyLine(unjustifiedLines.get(i),maxLineLength, i==unjustifiedLines.size()-1));
       }


        return justifiedLines;
    }

    private static List<String> justifyLine(List<String> line, int maxLineLength, boolean isLastLine) {
        List<String> justifiedLine  = new ArrayList<>();
        if(!isLastLine){

            int[] spaces = new int[line.size()-1];
            int totalReqdSpace = maxLineLength-getLineLengthWithoutSpace(line) ;
            for(int i=0;i<totalReqdSpace;i++){
                spaces[(spaces.length-1+( totalReqdSpace/spaces.length)*spaces.length-i)%spaces.length]++;

            }
            for(int i=0;i<line.size()-1;i++){
                justifiedLine.add(line.get(i));
                justifiedLine.add(getSpaces(spaces[i]));
            }
            justifiedLine.add(line.get(line.size()-1));
        }else{

            for(int i=0;i<line.size();i++){
                justifiedLine.add(line.get(i));
                if(i!=line.size()-1){

                    justifiedLine.add("_");
                }
            }
            int totalReqdSpace = maxLineLength-getLineLengthWithoutSpace(justifiedLine) ;
            String spaces = getSpaces(totalReqdSpace);
            justifiedLine.add(spaces);
        }

        return justifiedLine;
    }

    private static String getSpaces(int numSpaces) {
        StringBuilder spaces=new StringBuilder();
        while (numSpaces-->0){
            spaces.append('_');
        }

        return spaces.toString();
    }

    private static int getLineLength(List<String> currentLine) {
        int totalLength =getLineLengthWithoutSpace(currentLine);
        totalLength+=currentLine.size()-1;
        return totalLength;
    }
    private static int getLineLengthWithoutSpace(List<String> currentLine){
        int totalLength =0;
        for(String s:currentLine){
            totalLength+=s.length();
        }
        return totalLength;
    }
}
