package guidetocompetitiveprogramming;

public class Treaps<T extends Comparable<T>> {

    public static void main(String[] args) {
        Treaps<Integer> treaps = new Treaps<>();
        treaps.insert(1);
        treaps.insert(2);
        treaps.insert(3);
        treaps.insert(4);
        System.out.println(treaps.find(3).priority);
        System.out.println(treaps.find(2).priority);
        System.out.println(treaps.find(1).priority);
        System.out.println(treaps.find(4).priority);
    }


    private TreapNode<T> head;

    public void insert(T value) {
        int priority = TreapNode.getPriority();
        head = insert(head, value, priority);

    }

    public T getMin(){
        if(head==null){
            return null;
        }
        return head.value;
    }

    public TreapNode<T> find(T value){
      return   find(head,value);
    }

    private TreapNode<T> find(TreapNode<T> node, T value) {
        if(node==null){
            return null;
        }
        if(node.value.compareTo(value)==0){
            return node;
        }else if(node.value.compareTo(value)>0){
            return find(node.left,value);
        }else{
           return find(node.right,value);
        }

    }

    private TreapNode<T> insert(TreapNode<T> head, T value, int priority) {
        TreapNode<T> newNode = new TreapNode<>(value, priority);
        if (head == null) {
            return newNode;
        }
        if (priority < head.priority) {

            TreapNode<T>[] split = split(head, value);
            newNode.left = split[0];
            newNode.right = split[1];
            head = newNode;
        } else if (value.compareTo(head.value) <= 0) {
            head.left = insert(head.left, value, priority);
        } else {
            head.right = insert(head.right, value, priority);
        }
        return head;
    }

    public void remove(T value) {
        head = remove(head, value);
    }

    private TreapNode<T> remove(TreapNode<T> head, T value) {
        if (head == null) {
            return null;
        }
        if (head.value.equals(value)) {
            head = merge(head.left, head.right);
            return head;
        } else if (value.compareTo(head.value) < 0) {
            head.left = remove(head.left, value);
        } else {
            head.right = remove(head.right, value);
        }
        return head;
    }

    public static <T extends Comparable<T>> TreapNode<T>[] split(TreapNode<T> head, T value) {

        TreapNode<T> left = null;
        TreapNode<T> right = null;
        if(head!=null){
            if (head.value.compareTo(value) <= 0) {
                left = head;
                TreapNode<T>[] split = split(head.right, value);
                left.right = split[0];
                right = split[1];
            } else {
                right = head;
                TreapNode<T>[] split = split(head.left, value);
                left = split[0];
                right.left = split[1];
            }
        }


        return new TreapNode[]{left, right};
    }

    /**
     * all values in left are smaller than all values in right
     *
     * @param left
     * @param right
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> TreapNode<T> merge(TreapNode<T> left, TreapNode<T> right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        TreapNode<T> head = null;
        if (left.priority <= right.priority) {
            head = left;
            head.right = merge(head.right, right);
        } else {
            head = right;
            head.left = merge(head.left, left);
        }
        return head;
    }

}
