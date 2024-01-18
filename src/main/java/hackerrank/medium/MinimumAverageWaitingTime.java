package hackerrank.medium;

import epp.Pair;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MinimumAverageWaitingTime {

    public static long minimumAverage(List<List<Integer>> customers) {
        // Write your code here
        NavigableMap<Long,List<List<Integer>>> treeMap = new TreeMap<>();
        for(List<Integer> customer:customers){
            long arrivalTime = customer.get(0);
            List<List<Integer>> list = treeMap.getOrDefault(arrivalTime, new ArrayList<>());
            list.add(customer);
            treeMap.put(arrivalTime,list);
        }
        PriorityQueue<List<Integer>> waitingCustomers = new PriorityQueue<>(Comparator.comparingInt(l-> l.get(1)));
        long currentTime = 0;
        long totalWaitTime = 0;

        while (!treeMap.isEmpty()||!waitingCustomers.isEmpty()){
            if(waitingCustomers.isEmpty()){
                Map.Entry<Long, List<List<Integer>>> firstEntry = treeMap.pollFirstEntry();
                waitingCustomers.addAll(firstEntry.getValue());
                currentTime = firstEntry.getKey();
            }else {
                List<Integer> customer = waitingCustomers.poll();
                currentTime = currentTime + customer.get(1);
                long waitTime = currentTime - customer.get(0);
                totalWaitTime = totalWaitTime + waitTime;
                System.out.println(totalWaitTime);
                NavigableMap<Long, List<List<Integer>>> headMap = treeMap.headMap(currentTime, true);
                headMap.values().forEach(waitingCustomers::addAll);
                headMap.clear();
            }

        }
        return   (totalWaitTime/customers.size());
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> customers = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                customers.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        long result =  minimumAverage(customers);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    public static void main2(String[] args) {
        ArrayList<List<Integer>> customers = new ArrayList<>(List.of(
                new ArrayList<>(List.of(0, 3)),
                new ArrayList<>(List.of(1, 9)),
                new ArrayList<>(List.of(2, 6))
        ));
        System.out.println(minimumAverage(customers));

        customers = new ArrayList<>(List.of(
                new ArrayList<>(List.of(0, 3)),
                new ArrayList<>(List.of(1, 9)),
                new ArrayList<>(List.of(2, 5))
        ));
        System.out.println(minimumAverage(customers));





        customers = new ArrayList<>(List.of(
                new ArrayList<>(List.of( 961148050 ,385599125)),
                new ArrayList<>(List.of(951133776, 376367013)),
                new ArrayList<>(List.of(283280121 ,782916802)),
                new ArrayList<>(List.of( 317664929 ,898415172)),
                new ArrayList<>(List.of(  980913391, 847912645))
        ));
        System.out.println(minimumAverage(customers));
    }
}
