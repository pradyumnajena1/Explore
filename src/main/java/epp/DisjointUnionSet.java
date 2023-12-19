package epp;

public class DisjointUnionSet {
    private int n;
    private int[] parent;
    private int[] rank;
    private int[] size;

    public DisjointUnionSet(int n) {
        this.n = n;
        this.parent = new int[n];
        this.rank = new int[n];
        this.size = new int[n];
        makeSet();
    }

    // Creates n sets with single item in each
    void makeSet() {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
            rank[i] = 1;
        }
    }

    // Returns representative of x's set
    public int find(int x) {

        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {

        int xRoot = find(x), yRoot = find(y);
        if (xRoot == yRoot) return;
        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
            size[yRoot] = size[yRoot] + size[xRoot];
        } else if (rank[yRoot] < rank[xRoot]) {
            parent[yRoot] = xRoot;
            size[xRoot] = size[yRoot] + size[xRoot];
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot] = rank[xRoot] + 1;
            size[xRoot] = size[yRoot] + size[xRoot];
        }
    }
    public  int getSize(int x){
        int xRoot = find(x);
        return size[xRoot];
    }

}
