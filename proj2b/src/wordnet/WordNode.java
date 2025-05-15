package wordnet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WordNode {
    // 创建基础节点
    private final int ID;
    private final String define;

    private Collection<String> synonymList;
    private List<WordNode> neighbors;


    public WordNode(int id, String define, Collection<String> synonymList) {
        this.ID = id;
        this.define = define;
        this.synonymList = synonymList;
        this.neighbors = new ArrayList<>();
    }

    public void addNeighbors(WordNode neighbor) {
        this.neighbors.add(neighbor);
    }

    public List<WordNode> getNeighbors() {
        return this.neighbors;
    }


    public int getID() {
        return ID;
    }

    public String getDefine() {
        return this.define;
    }

    @Override
    public String toString() {

        return this.synonymList.toString();
    }

}
