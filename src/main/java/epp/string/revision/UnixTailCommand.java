package epp.string.revision;

import java.io.*;

public class UnixTailCommand {
    public static void main(String[] args) throws IOException {
        unixTailCommand("C:\\Users\\Pradyumna\\IdeaProjects\\Explore\\src\\main\\java\\epp\\string\\revision" +
                "\\UnixTailCommand.java",31);
    }

    private static void unixTailCommand(String filePath, int numLines) throws IOException {
        String[] buffer = new String[numLines];
        int writeIndex = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream( filePath)));
        String line = null;
        int lineCount = 0;
        while ((line = br.readLine())!=null){
            lineCount++;
            buffer[writeIndex++%numLines] = line;


        }
        lineCount = Math.min(lineCount,numLines);
        int startIndex =lineCount<numLines?0: (writeIndex )%numLines;
        for(int i=0;i<lineCount;i++){
            System.out.println(buffer[(startIndex+i)%numLines]);
        }
    }
}
