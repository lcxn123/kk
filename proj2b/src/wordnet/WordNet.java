package wordnet;

import com.puppycrawl.tools.checkstyle.grammar.javadoc.JavadocParser;
import edu.princeton.cs.algs4.In;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordNet {
    private List<String[]> wordList;
    private List<String[]> numberlist;

    private Map<String, Integer> synonym;
    private Map<Integer, String[]> IDMap;

    private DirectedGraph graph;

    public WordNet(String wordsFilename, String filename2) {

        // 初始化存储结构
        wordList = new ArrayList<>();
        synonym = new HashMap<>();
        numberlist = new ArrayList<>();
        IDMap = new HashMap<>();
        // 初始化有向图

        graph = new DirectedGraph();

        // 读取文件
        try (BufferedReader br = new BufferedReader(new FileReader(wordsFilename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 按逗号分割
                String[] values = line.split(",");

                for (int i = 0; i < values.length; i++) {
                    if (i == 1) { // 处理第二部分（索引为1）
                        // 按一个或多个空格分割，并过滤空字符串
                        String[] parts = values[i].split("\\s+");
                        IDMap.put(Integer.valueOf(values[0]), parts);
                        if (parts != null) {
                            for (String part : parts) {
                                synonym.put(part, Integer.valueOf(values[0]));
                            }

                        }
                    }
                }
                wordList.add(values);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


        try (BufferedReader br = new BufferedReader(new FileReader(filename2))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 逗号分隔
                String[] values = line.split(",");

                // 储存数据
                numberlist.add(values);
            }
        } catch (IOException a) {
            a.printStackTrace();
        }

        // 把数据编程有向图的节点
        for (String[] word : wordList) {
            int id = Integer.parseInt(word[0]);
            String define = word[2];
            graph.addNode(id, define);
        }

        // 连接边
        for (String[] id : numberlist) {
            int sourceId = Integer.parseInt(id[0]);

            for (int i = 1; i < id.length; i++) {
                int destId = Integer.parseInt(id[i]);
                graph.addEdge(sourceId, destId);
            }

        }


    }


    public WordNode getWordNode(String word) {
        int id = synonym.get(word);
        return graph.getWord(id);
    }


    public List<WordNode> getHyponyms(Integer id) {
        WordNode start = graph.getWord(id);
        if (start == null) {
            return null;
        }

        List<Integer> wordID = graph.bfs(start);
        List<WordNode> wordNodes = new ArrayList<>();
        for (Integer ID : wordID) {
            WordNode node = graph.getWord(ID);
            if (node == null) {
                continue;
            }
            wordNodes.addLast(node);
        }
        return wordNodes;
    }

    public int getId(String word) {
        if (!synonym.containsKey(word)) {
            return -1;
        }
        return synonym.get(word);
    }


    public String[] getSameWord(String word) {
        int id = getId(word);
        return IDMap.get(id);
    }
}
