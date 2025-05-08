import java.util.Arrays;

public class UnionFind {
    // TODO: Instance variables

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    private int[] SearchArray;
    private int size;
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        size = N;
        SearchArray = new int[N];
        Arrays.fill(SearchArray,-1);
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE

        return Math.abs(SearchArray[find(v)]);
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        return -1;
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (v1 < 0 || v2 < 0|| v1 >= size|| v2 >= size) {
            throw new IllegalArgumentException("Some comment to describe the reason for throwing.");
        }
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        if (v < 0 || v >= size) {throw new IllegalArgumentException("Some comment to describe the reason for throwing.");}
        if (SearchArray[v] < 0) {return v;}
        int r = v;
        while(SearchArray[r] >= 0){
            r = SearchArray[r];
        }

        int f = v;
        int next;
        while(SearchArray[f] >= 0){
            next = SearchArray[f];
            SearchArray[f] = r;
            f = next;
        }
        return r;
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (v1 < 0|| v2 < 0|| v1 >= size|| v2 >= size) {
            throw new IllegalArgumentException("Some comment to describe the reason for throwing.");
        }
        if (v1 == v2) {
            return;
        }
        int root1 = find(v1);
        int root2 = find(v2);
        if (SearchArray[root1] < SearchArray[root2]) {
            SearchArray[root1] += SearchArray[root2];
            SearchArray[root2] = root1;
        } else{
            SearchArray[root2] += SearchArray[root1];
            SearchArray[root1] = root2;
        }

    }
}
