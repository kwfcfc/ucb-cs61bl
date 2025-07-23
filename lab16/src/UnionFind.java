public class UnionFind {

    private final int[] unionSet;
    private final int capacity;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        unionSet = new int[N];
        for (int i = 0; i < N; i++) {
            unionSet[i] = -1;
        }
        capacity = N;
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        if (v < 0 || v >= capacity) {
            throw new IllegalArgumentException();
        }
        int root = find(v);
        return -unionSet[root];
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        if (v < 0 || v >= capacity) {
            throw new IllegalArgumentException();
        }
        return unionSet[v];
    }

    /* Returns true if nodes V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);
        return root1 == root2;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        if (v < 0 || v >= capacity) {
            throw new IllegalArgumentException("element out of bound");
        }
        if (unionSet[v] < 0) {
            return v;
        } else {
            unionSet[v] = find(unionSet[v]);
            return unionSet[v];
        }
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. */
    public void union(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);
        if (root1 == root2) {
            return;
        }
        int rootCompare = sizeOf(root1) - sizeOf(root2);

        if (rootCompare > 0) {
            unionSet[root1] += unionSet[root2];
            unionSet[root2] = root1;
        } else {
            unionSet[root2] += unionSet[root1];
            unionSet[root1] = root2;
        }
    }
}
