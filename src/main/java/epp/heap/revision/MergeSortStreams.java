package epp.heap.revision;

import java.util.*;

public class MergeSortStreams {
  public static void main(String[] args) {
    List<List<Integer>> streams = new ArrayList<List<Integer>>();
    streams.add(new ArrayList<Integer>(List.of(1, 2, 3)));
    streams.add(new ArrayList<Integer>(List.of(2, 3, 4, 5)));
    streams.add(new ArrayList<Integer>(List.of(1, 3, 5, 9)));

    System.out.println(mergeSortStreams(streams));
  }

  public static List<Integer> mergeSortStreams(List<List<Integer>> streams) {
    List<Integer> result = new ArrayList< >();
    List<Iterator<Integer>> iterators = new ArrayList<>();
    for (List<Integer> stream : streams) {
      iterators.add(stream.iterator());
    }
    PriorityQueue<StreamEntry> minHeap =
        new PriorityQueue<>(Comparator.comparingInt(StreamEntry::getValue));
    for (int i = 0; i < iterators.size(); i++) {
      if (iterators.get(i).hasNext()) {
        minHeap.offer(new StreamEntry(iterators.get(i).next(), i));
      }
    }
    while (!minHeap.isEmpty()) {
      StreamEntry header = minHeap.poll();
      result.add(header.getValue());
      int streamIndex = header.getStreamIndex();
      if (iterators.get(streamIndex).hasNext()) {
        minHeap.offer(new StreamEntry(iterators.get(streamIndex).next(), streamIndex));
      }
    }
    return result;
  }

  private static class StreamEntry {
    int value;
    int streamIndex;

    public StreamEntry(int value, int streamIndex) {
      this.value = value;
      this.streamIndex = streamIndex;
    }

    public int getValue() {
      return value;
    }

    public int getStreamIndex() {
      return streamIndex;
    }
  }
}
