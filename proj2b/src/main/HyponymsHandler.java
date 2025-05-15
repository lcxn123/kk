package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import wordnet.WordNet;
import wordnet.WordNode;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {

    private final WordNet wordNet;

    public HyponymsHandler() {
        super();
        wordNet = new WordNet("data/wordnet/synsets.txt", "data/wordnet/hyponyms.txt");
    }

    public HyponymsHandler(String WordFilename, String filename) {
        super();
        wordNet = new WordNet(WordFilename, filename);
    }

    public HyponymsHandler(String wordFile, String countFile, String synsetFile, String hyponymFile) {
        super();
        wordNet = new WordNet(synsetFile, hyponymFile);
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        if (words.isEmpty()) {
            return "No words provided.";
        }

        // 用于存储所有下位词的集合（自动去重）
        Set<String> allHyponymWords = new HashSet<>();

        for (String word : words) {
            Integer[] ids = wordNet.getId(word);
            if (ids == null) {
                continue; // 跳过不存在的单词
            }

            List<WordNode> allHyponymNodes = new ArrayList<>();

            for (int id : ids) {
                Set<WordNode> wordList = wordNet.getHyponyms(id);
                if (wordList != null) {
                    allHyponymNodes.addAll(wordList);
                }
                allHyponymNodes.add(wordNet.getWordNode(id));
            }


            allHyponymWords.add(word);
            for (WordNode node : allHyponymNodes) {
                allHyponymWords.add(node.toString());
            }
        }

        // 如果没有找到任何下位词
        if (allHyponymWords.isEmpty()) {
            return "No hyponyms found.";
        }



        List<String> flatList = new ArrayList<>();
        for (String item : allHyponymWords) {
            // 处理子列表形式的元素
            if (item.startsWith("[") && item.endsWith("]")) {
                String[] wordss = item.substring(1, item.length() - 1).split(", ");
                flatList.addAll(Arrays.asList(wordss));
            }
            // 处理单独的单词
            else {
                flatList.add(item);
            }
        }

        Set<String> newset = new HashSet<>(flatList);

        // 转换为列表并排序
        List<String> sortedWords = new ArrayList<>(newset);
        Collections.sort(sortedWords);

        // 格式化为 [word1, word2, ...]
        return "[" + String.join(", ", sortedWords) + "]";
        }
    }