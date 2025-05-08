package Sets;

public class ListOfSets implements DisjoinSets{

    private int[] set;

    public ListOfSets(int x){}
    @Override
    public boolean inConnected(int p, int q) {
        return false;
    }

    @Override
    public void connect(int p, int q) {

    }
}
