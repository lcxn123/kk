package Sets;

public class QuickFindDS implements DisjoinSets{

    private int[] id;
    @Override
    public boolean inConnected(int p, int q) {
        return id[p] == id[q];
    }

    @Override
    public void connect(int p, int q) {
        int pid = id[p];
        int qid = id[q];

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }
}
