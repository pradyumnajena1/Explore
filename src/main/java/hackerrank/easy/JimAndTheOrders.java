package hackerrank.easy;

import epp.Triplet;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class JimAndTheOrders {
    public static void main(String[] args) throws IOException {



        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> orders = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                orders.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        List<Integer> result =  jimOrders(orders);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();

         System.out.println(jimOrders(new ArrayList<>(List.of(List.of(422000 ,268395), List.of(801724 ,761610) ))));
    }

    public static List<Integer> jimOrders(List<List<Integer>> orders) {
        // Write your code here
        //0->orderId
        //1->prep time
        // need to preserve cust id , so saving the location
        if(orders.size()==0){
            return new ArrayList<>();
        }

        List<Triplet<Integer,Integer,Integer>> triplets = new ArrayList<>();
        for(int i=0;i<orders.size();i++){
            triplets.add(new Triplet<>(i+1,orders.get(i).get(0),orders.get(i).get(1)));
        }
        triplets.sort(Comparator
                .comparingLong( (Triplet<Integer,Integer,Integer> x)-> ((long)  x.getSecond())+x.getThird())
                .thenComparingInt((Triplet<Integer,Integer,Integer> x)->x.getFirst()));
        List<Integer> customerIds = triplets.stream().map(x -> x.getFirst()).collect(toList());
        return customerIds;
    }
}
