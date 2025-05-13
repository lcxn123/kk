package wordnet;

import java.util.ArrayList;
import java.util.List;

public class WordNode {
    // 创建基础节点
    private final int ID;
    private final String define;
    private List<WordNode> neighbors;


    public WordNode(int id, String define) {
        this.ID = id;
        this.define = define;
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
        return define;
    }
}
