package wordnet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordNet {
    private Map<String, Integer[]> synonym;

    private DirectedGraph graph;

    public WordNet(String wordsFilename, String filename2) {

        // 初始化存储结构
        List<String[]> numberlist = new ArrayList<>();
        List<String> synonymWordList = new ArrayList<>();


        synonym = new HashMap<>();

        // 初始化有向图

        graph = new DirectedGraph();

        // 读取同义词集文件
        try (BufferedReader br = new BufferedReader(new FileReader(wordsFilename))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");

                for (int i = 0; i < values.length; i++) {

                    if (i == 1) {
                        String[] parts = values[i].split("\\s+");
                            for (String part : parts) {

                                if (synonym.containsKey(part)){
                                    Integer[] array = synonym.get(part);
                                    Integer[] newArray = Arrays.copyOf(array, array.length + 1);
                                    newArray[newArray.length - 1] = Integer.valueOf(values[0]);
                                    synonym.put(part,newArray);
                                } else {
                                    Integer[] array = {Integer.valueOf(values[0])};
                                    synonym.putIfAbsent(part, array);
                                }

                                synonymWordList.add(part);
                        }
                    }
                }

                graph.addNode(Integer.parseInt(values[0]),values[2],synonymWordList);

                synonymWordList.clear();

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


        // 连接边
        for (String[] id : numberlist) {
            int sourceId = Integer.parseInt(id[0]);

            for (int i = 1; i < id.length; i++) {
                int destId = Integer.parseInt(id[i]);
                graph.addEdge(sourceId, destId);
            }

        }


    }

    public Set<WordNode> getHyponyms(Integer id) {

        WordNode start = graph.getWord(id);
        if (start == null) {
            return null;
        }

        List<Integer> wordID = graph.bfs(start);


        Set<WordNode> wordNodes = new HashSet<>();

        for (Integer ID : wordID) {
            WordNode node = graph.getWord(ID);
            if (node == null) {
                continue;
            }
            wordNodes.add(node);
        }

        return wordNodes;
    }

    public Integer[] getId(String word) {
        if (!synonym.containsKey(word)) {
            return new Integer[0];
        }
        return synonym.get(word);
    }


    public WordNode getWordNode(int id){
        return graph.getWord(id);
    }
}
