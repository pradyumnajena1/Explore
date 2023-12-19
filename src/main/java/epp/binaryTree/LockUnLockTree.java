package epp.binaryTree;

public class LockUnLockTree {

    BinaryTreeNodeWithSizeAndParent<Integer> root;

    public LockUnLockTree(BinaryTreeNodeWithSizeAndParent<Integer> root) {
        this.root = root;
    }

    /**
     * to lock , no ancestor and no descedants must not be locked
     * @param node
     * @return
     */
    public boolean lock(BinaryTreeNodeWithSizeAndParent<Integer> node){
        if(node.locked){
            return true;
        }
        if(node.size>0){
            return false;
        }
        BinaryTreeNodeWithSizeAndParent<Integer> current  = node.parent;
        while (current!=null){
            if(current.locked){
                return false;
            }
            current = current.parent;
        }
        node.locked = true;
          current  = node;
        while (current!=null){
            current.size++;
             current=current.parent;
        }
         return true;


    }
    public void unlock(BinaryTreeNodeWithSizeAndParent<Integer> node){
        if(node.locked==false){
            return;
        }
        BinaryTreeNodeWithSizeAndParent<Integer> current = node;
        while (current!=null){
            current.size--;
            current = current.parent;
        }
    }
    public boolean isLocked(BinaryTreeNodeWithSizeAndParent<Integer> node){
        return node.locked;
    }

    public static void main(String[] args) {
        BinaryTreeNodeWithSizeAndParent<Integer> root = new BinaryTreeNodeWithSizeAndParent<>(8,
                new BinaryTreeNodeWithSizeAndParent<>(5,new BinaryTreeNodeWithSizeAndParent<>(3,new BinaryTreeNodeWithSizeAndParent<>(0),
                        new BinaryTreeNodeWithSizeAndParent<>(2)),
                        new BinaryTreeNodeWithSizeAndParent<>(17,null,new BinaryTreeNodeWithSizeAndParent<>(12,new BinaryTreeNodeWithSizeAndParent<>(1),null))),
                new BinaryTreeNodeWithSizeAndParent<>(21,new BinaryTreeNodeWithSizeAndParent<>(10,new BinaryTreeNodeWithSizeAndParent<>(14,null,new BinaryTreeNodeWithSizeAndParent<>(19)),
                        new BinaryTreeNodeWithSizeAndParent<>(4)),
                        new BinaryTreeNodeWithSizeAndParent<>(7,null,new BinaryTreeNodeWithSizeAndParent<>(8)))
        );


        LockUnLockTree lockUnLockTree = new LockUnLockTree(root);

        lockUnLockTree.lock(root.left.left);
        System.out.println(root.left.left);
        System.out.println(root.left);
        System.out.println(root );

        System.out.println(lockUnLockTree.lock(root.right.right));
        System.out.println(root.right.right);
        System.out.println(root.right);
        System.out.println(root);

    }
}
