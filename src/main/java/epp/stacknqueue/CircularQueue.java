package epp.stacknqueue;

public class CircularQueue {
    private int size;
    private int start;
    private int end;
    private int[] data;

    public CircularQueue(int size) {
        this.size = size;
        data = new int [size];
    }

    public void enqueue(int value){
        if(full()){
            throw new IllegalStateException("Queue is full");
        }
        data[end] = value;
        end = (end+1)%size;

    }
    public int dequeue(){
        if(empty()){
            throw new IllegalStateException("queue is empty");
        }
        int item = data[start];
        start = (start+1)%size;
        return item;
    }
    public boolean empty(){
        return start==end;
    }
    public boolean full(){
        return (end+1)%size == start;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CircularQueue{");
        sb.append("size=").append(size);
        sb.append(", start=").append(start);
        sb.append(", end=").append(end);
        sb.append(", data=");
        if (data == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < data.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(data[i]);
            sb.append(']');
        }
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) {
        CircularQueue circularQueue = new CircularQueue(5);
        circularQueue.enqueue(1);
        circularQueue.enqueue(2);
        circularQueue.enqueue(3);
        circularQueue.enqueue(4);
        System.out.println(circularQueue);
        circularQueue.dequeue();
        circularQueue.enqueue(5);
        System.out.println(circularQueue);
        circularQueue.dequeue();

        circularQueue.enqueue(6);
        System.out.println(circularQueue);
    }
}
