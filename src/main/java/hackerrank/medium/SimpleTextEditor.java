package hackerrank.medium;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class SimpleTextEditor {

    private Stack<String> opStack = new Stack<>();

    public SimpleTextEditor(String initialText) {
        opStack.push(initialText);
    }
    public SimpleTextEditor( ) {
        this("");
    }
    public void executeCommands(List<List<String>> commands){

        for(List<String> op:commands){
             executeCommand(op);
        }

    }
    public void executeCommand(List<String> command){
        if(command.get(0).equals("1")){
            String  text = opStack.peek();
            text = text + command.get(1);
            opStack.push(text);
        } else if (command.get(0).equals("2")) {
            int deleteCount = Integer.parseInt(command.get(1));
            String  text = opStack.peek();
            text=text.substring(0,text.length()-deleteCount);
            opStack.push(text);
        } else if (command.get(0).equals("3")) {
            int printIndex = Integer.parseInt(command.get(1));
            String  text = opStack.peek();
            System.out.println(text.charAt(printIndex-1));
        } else if (command.get(0).equals("4")) {
            opStack.pop();
        }
    }

    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader( System.in));
        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);
        List<List<String>> operations = new ArrayList<>();

        IntStream.range(0, n ).forEach(i -> {
            try {
                operations.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))

                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        System.out.println(operations);
        SimpleTextEditor textEditor = new SimpleTextEditor("");
         textEditor.executeCommands(operations);

    }
}
