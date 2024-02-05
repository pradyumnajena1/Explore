package hackerrank.medium;

import guidetocompetitiveprogramming.LazySegmentTree;
import guidetocompetitiveprogramming.SegmentTreeNode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class BoxOperations {

    public static List<Integer> solve(List<Integer> box,List<List< Integer>> queries){
        List<Integer> result = new ArrayList<>();
        LazySegmentTree segmentTree = new LazySegmentTree(box);
       // segmentTree.printTree();
        for(List< Integer> query:queries){
            System.out.println(query);
            int type= query.get(0);
            int l = query.get(1);
            int r = query.get(2);
            if(type==1){
               int delta = query.get(3);
               segmentTree.updateRange(l,r,delta,true);

            } else if (type==2) {
                int delta = query.get(3);
                segmentTree.updateRange(l,r,delta,false);

            } else if (type==3) {
                SegmentTreeNode segmentTreeNode = segmentTree.rangeResult(l, r);
                result.add(segmentTreeNode.getMin());
            }else {
                SegmentTreeNode segmentTreeNode = segmentTree.rangeResult(l, r);
                result.add(segmentTreeNode.getSum());

            }
            segmentTree.printTree();
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int q = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> box = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());
        List< List<Integer>> queries = new ArrayList<>();
        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Write your code here

        List<Integer> result = solve(box, queries);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n" );
        bufferedReader.close();
        bufferedWriter.close();
    }
}
