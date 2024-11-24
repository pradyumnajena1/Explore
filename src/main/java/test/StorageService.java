package test;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StorageService {
    private File targetFile;
    private Map<Integer, LineNumberReader> readerIndexMap;
    private Set<String> seenKeys;

    private BufferedWriter writer;


    public StorageService(File targetFile) throws IOException {
        this.targetFile = targetFile;
        this.readerIndexMap = new HashMap<>();
        this.seenKeys = new HashSet<>();
        this.writer = new BufferedWriter(new FileWriter(targetFile));
    }

    public void add(String key,String value) throws IOException {
          if(!seenKeys.contains(key)){
              writer.write (new Record(key,value).toString());
              writer.newLine();
              seenKeys.add(key);
          }

    }

    public void add(Record record){


    }

    public Record getNextRecord(int groupId) throws IOException {
        String line = readerIndexMap.computeIfAbsent(groupId, x -> {
            try {
                return new LineNumberReader(new FileReader(targetFile));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).readLine();
        return Record.parse(line);
    }







    private static class Record{
        String key;
        String value;

        public Record(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public static Record parse(String line) {
            return null;
        }
    }


}
