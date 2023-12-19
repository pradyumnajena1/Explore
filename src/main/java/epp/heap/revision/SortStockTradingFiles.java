package epp.heap.revision;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class SortStockTradingFiles {
    public static void main(String[] args) throws IOException {
        Path path1 = new File("C:\\Users\\Pradyumna\\IdeaProjects\\Explore\\src\\main\\java\\epp\\heap\\revision" +
                "\\StockTrading1.txt").toPath();
        Path path2 = new File("C:\\Users\\Pradyumna\\IdeaProjects\\Explore\\src\\main\\java\\epp\\heap\\revision" +
                "\\StockTrading2.txt").toPath();
        Path path3 = new File("C:\\Users\\Pradyumna\\IdeaProjects\\Explore\\src\\main\\java\\epp\\heap\\revision" +
                "\\StockTrading3.txt").toPath();
        Path resultFile = new File("C:\\Users\\Pradyumna\\IdeaProjects\\Explore\\src\\main\\java\\epp\\heap\\revision" +
                "\\StockTradingResult.txt").toPath();

        mergeStockTradingFiles(List.of(path1, path2, path3), resultFile);

    }

    private static void mergeStockTradingFiles(List<Path> inputFiles, Path resultFile) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(resultFile, Charset.defaultCharset());
        PriorityQueue<Record> priorityQueue = new PriorityQueue<>();
        RecordFetcher fetcher = new RecordFetcher(inputFiles);
        for (Path path : inputFiles) {
            priorityQueue.offer(fetcher.getNextRecord(path));
        }
        while (!priorityQueue.isEmpty()) {
            Record polled = priorityQueue.poll();
            writeRecord(writer, polled);
            Record nextRecord = fetcher.getNextRecord(polled.path);
            if (nextRecord != null) {
                priorityQueue.offer(nextRecord);
            }
        }

        writer.close();
        fetcher.close();
    }

    private static void writeRecord(BufferedWriter writer, Record polled) throws IOException {
        writer.write(polled.toString());
        writer.newLine();
        writer.flush();
    }

    private static class RecordFetcher {
        List<Path> inputPaths;
        Map<String, BufferedReader> readersMap;

        public RecordFetcher(List<Path> inputPaths) throws IOException {
            this.inputPaths = inputPaths;
            this.readersMap = new HashMap<>();
            for (Path path : inputPaths) {
                this.readersMap.put(path.toString(), Files.newBufferedReader(path, Charset.defaultCharset()));
            }
        }

        public Record getNextRecord(Path path) throws IOException {
            String line = readersMap.get(path.toString()).readLine();

            Record record = Record.parse(line);
            if(record!=null){

                record.setPath(path);
            }
            return record;
        }

        public void close() {
            for (Path path : inputPaths) {
                try {
                    readersMap.get(path.toString()).close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }

    private static class Record implements Comparable<Record> {
        private long timeStamp;
        private String symbol;
        private int numUnits;
        private double price;
        private Path path;

        public Record(long timeStamp, String symbol, int numUnits, double price) {
            this.timeStamp = timeStamp;
            this.symbol = symbol;
            this.numUnits = numUnits;
            this.price = price;
        }

        @Override
        public int compareTo(Record o) {
            return Long.compare(this.timeStamp, o.timeStamp);
        }

        public long getTimeStamp() {
            return timeStamp;
        }

        public String getSymbol() {
            return symbol;
        }

        public int getNumUnits() {
            return numUnits;
        }

        public double getPrice() {
            return price;
        }

        public Path getPath() {
            return path;
        }

        public void setPath(Path path) {
            this.path = path;
        }

        public static Record parse(String line) {
            if (line == null || line.isBlank()) {
                return null;
            }
            String[] split = line.split(",");
            for (int i = 0; i < split.length; i++) {
                split[i] = split[i].trim();
            }
            long timeStamp = Long.parseLong(split[0]);
            String symbol = split[1].trim();
            int numUnits = Integer.parseInt(split[2]);
            double price = Double.parseDouble(split[3]);
            return new Record(timeStamp, symbol, numUnits, price);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(timeStamp).append(", ");
            sb.append(symbol).append(", ");
            sb.append(numUnits).append(", ");
            sb.append(price);
            return sb.toString();
        }
    }
}
