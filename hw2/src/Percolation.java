import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/*  WeightedQuickUnionUF {
       public WeightedQuickUnionUF(int n)      // creates a UF with n elements
       public int count()                      // number of disjoint sets
       public int find(int p)                  // the root of p's set
       public boolean connected(int p, int q)  // whether p and q are in the same set
       public void union(int p, int q)         // join p and q's sets together
*/

public class Percolation {

    private WeightedQuickUnionUF Union;
    private boolean[][] gird;

    private int openNum;

    private final int topWater;
    private final int bottomWater;
    private final int N;



    public Percolation(int N) {
        Union = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < N-1; i++) {
            Union.union(i,i+1);
        }
        for (int i = N*N-N; i <N*N-1 ; i++) {
            Union.union(i,i+1);
        }
        gird = new boolean[N][N];
        openNum = 0;
        this.N = N;
        topWater = N * N;
        bottomWater = N * N + 1;
        Union.union(0,topWater);
        Union.union(N*N-1,bottomWater);
    }

    public void open(int row, int col) {
        if (row > N || col > N){throw new java.lang.IndexOutOfBoundsException("超出范围");}
        if (row < 0 || col < 0){throw new java.lang.IllegalArgumentException("不能小于0");}
        gird[row][col] = true;
        openNum++;
        OpenConnect(row,col);
    }

    public boolean isOpen(int row, int col) {
        if (row > N || col > N){throw new java.lang.IndexOutOfBoundsException("超出范围");}
        if (row < 0 || col < 0){throw new java.lang.IllegalArgumentException("不能小于0");}
        return gird[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row > N || col > N){throw new java.lang.IndexOutOfBoundsException("超出范围");}
        if (row < 0 || col < 0){throw new java.lang.IllegalArgumentException("不能小于0");}
        if(isOpen(row, col)){
        int position = row * N + col;
        return Union.connected(topWater,position);
        }
        return false;
    }

    public int numberOfOpenSites() {
        return openNum;
    }

    public boolean percolates() {
        return Union.connected(topWater,bottomWater);
    }

    // Add helper methods
    private void OpenConnect(int row , int col){
        int position = row * N + col;
        if (isOpen(row, col)) {
            // 向下连接
            if (! Union.connected(position,bottomWater) && isOpen(row+1, col)){
                Union.union(position,position + N);

            }
            // 向上连接
            if (! Union.connected(position,topWater) && isOpen(row-1, col)){
                Union.union(position,position - N);

            }
            // 向左连接
            if ((position % N != 0) && isOpen(row,col-1)){
                Union.union(position,position-1);

            }

            // 向右连接
            if ((position % N != N-1) && isOpen(row,col+1)){
                Union.union(position,position+1);

            }


        }
    }

    //
    //

}
