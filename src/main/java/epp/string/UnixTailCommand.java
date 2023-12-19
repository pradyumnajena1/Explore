package epp.string;

import java.io.*;
import java.util.Arrays;

public class UnixTailCommand {

    private File file;
    private int numLines;
    private String[] cbuffer;
    private int index=-1;
    public UnixTailCommand(String path, int numLines){
        this(new File(path),numLines);
    }

    public UnixTailCommand(File file, int numLines) {
        this.file = file;
        this.numLines = numLines;
        this.cbuffer = new String[numLines];
    }

    public void read() throws FileNotFoundException {

     try(  BufferedReader br = new BufferedReader(new FileReader(file))){
         String line = null;
         while ((line = br.readLine())!=null){
             cbuffer[++index%numLines] = line;

         }

     } catch (IOException e) {
         throw new RuntimeException(e);
     }

    }
    public String[] getLastLines(){
        String[] lines = new String[(numLines)];
        int readIndex = index;
        int writeIndex = numLines-1;
        for(int i=0;i<numLines;i++){
            String line = cbuffer[(readIndex + numLines) % numLines];
            readIndex--;
            lines[writeIndex--] = line;
        }
        return lines;

    }

    public static void main(String[] args) throws FileNotFoundException {
        UnixTailCommand tailCommand = new UnixTailCommand("C:\\Users\\Pradyumna\\IdeaProjects\\Explore\\src\\main" +
                "\\java\\ds\\string\\UnixTailCommand.java",5);
        tailCommand.read();
         String[] lastLines = tailCommand.getLastLines();
        System.out.println(Arrays.toString( lastLines));
    }
}
