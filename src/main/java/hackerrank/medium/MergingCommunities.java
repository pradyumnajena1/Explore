package hackerrank.medium;

import epp.DisjointUnionSet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MergingCommunities {



    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);
        int q = Integer.parseInt(firstMultipleInput[1]);

        List<List<String>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))

                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        processQueries(n, queries, bufferedWriter);


        bufferedReader.close();
        bufferedWriter.close();
    }

    private static void processQueries(int n, List<List<String>> queries, BufferedWriter bufferedWriter) throws IOException {
        DisjointUnionSet disjointUnionSet = new DisjointUnionSet(n +1);
        for(int i = 0; i< queries.size(); i++){
            List<String> query = queries.get(i);
            if(query.get(0).equals("M")){
                int a = Integer.parseInt(query.get(1));
                int b = Integer.parseInt(query.get(2));
                disjointUnionSet.union(a,b);

            }else if(query.get(0).equals("Q")){
                int a = Integer.parseInt(query.get(1));
                bufferedWriter.write(String.valueOf( disjointUnionSet.getSize(a)));
                bufferedWriter.newLine();
            }
        }
    }
}
