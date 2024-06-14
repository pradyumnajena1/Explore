package epp.stacknqueue.revision;

public class QueueWithMax2<T extends Comparable<T>> {

  private MaxStack2<T> enqueue;
  private MaxStack2<T> dequeue;

  public QueueWithMax2() {
    enqueue = new MaxStack2<>();
    dequeue = new MaxStack2<>();
  }

  public void enqueue(T data) {
    enqueue.push(data);
  }

  public T dequeue() {
    if (dequeue.isEmpty()) {
      while (!enqueue.isEmpty()) {
        dequeue.push(enqueue.pop());
      }
    }
    if (!dequeue.isEmpty()) return dequeue.pop();
    throw new IllegalStateException("Empty queue");
  }

  public T max() {
    if (!enqueue.isEmpty()) {
      if (!dequeue.isEmpty()) {
        return enqueue.max().compareTo(dequeue.max()) >= 0 ? enqueue.max() : dequeue.max();
      }
      return enqueue.max();
    }
    if (!dequeue.isEmpty()) {
      return dequeue.max();
    }
    throw new IllegalStateException("Empty queue");
  }
}
