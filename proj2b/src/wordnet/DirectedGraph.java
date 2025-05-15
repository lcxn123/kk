package wordnet;

import java.util.*;

public class DirectedGraph {
    // 创建有向图
    private Map<Integer, WordNode> nodes;

    public DirectedGraph() {
        nodes = new TreeMap<>();
    }

    public void addNode(int id, String define,List<String> synonymList) {
        List<String> synonm = new ArrayList<>();
        synonm.addAll(synonymList);
        WordNode newnode = new WordNode(id, define,synonm);

        nodes.putIfAbsent(id, newnode);
    }

    public Boolean addEdge(int sourceId, int destId) {
        WordNode source = nodes.get(sourceId);
        WordNode dest = nodes.get(destId);
        if (source == null || dest == null) {
            return false;
        }

        source.addNeighbors(dest);
        return true;
    }


    public Collection<WordNode> getAllNodes() {
        return nodes.values();
    }

    public WordNode getWord(int id) {
        return nodes.get(id);
    }


    public List<Integer> bfs(WordNode start) {
        List<Integer> visitOrder = new ArrayList<>();
        Queue<WordNode> queue = new LinkedList<>();
        Set<WordNode> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            WordNode current = queue.poll();
            if (!current.equals(start)) {
                visitOrder.add(current.getID());
            }
            for (WordNode neighbor : current.getNeighbors()) {
                if (visited.add(neighbor)) {
                    queue.add(neighbor);
                }

            }
        }

        return visitOrder;
    }
}
