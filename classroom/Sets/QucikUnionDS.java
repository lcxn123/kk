package Sets;

public class QucikUnionDS implements DisjoinSets{

    private int[] parent;

    public QucikUnionDS(int N){
        // 初始化数组 -1
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = -1;
        }
    }

    public int find(int p){
        //找 p 的boss
        int r = p;
        while(parent[r] >= 0){
            r = parent[r];
        }
        return r;
    }
    @Override
    public boolean inConnected(int p, int q) {
      return find(p) == find(q);
    }

    @Override
    public void connect(int p, int q) {
        int i = find(p);
        int j = find(q);
        parent[i] = j;
    }
}
