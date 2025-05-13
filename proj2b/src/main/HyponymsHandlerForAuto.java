package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import wordnet.WordNet;
import wordnet.WordNode;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class HyponymsHandlerForAuto extends NgordnetQueryHandler {

    private final WordNet wordNet;


    public HyponymsHandlerForAuto() {
        super();
        wordNet = new WordNet("data/wordnet/synsets.txt", "data/wordnet/hyponyms.txt");
    }


    public HyponymsHandlerForAuto(String wordFile, String countFile, String synsetFile, String hyponymFile) {
        super();
        wordNet = new WordNet(synsetFile, hyponymFile);
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        if (words.isEmpty()) {
            return "No words provided.";
        }

        StringBuilder result = new StringBuilder();


        for (String word : words) {
            int id = wordNet.getId(word);
            List<WordNode> wordList = wordNet.getHyponyms(id);

            if (wordList == null) {
                continue;
            }
            for (WordNode node : wordList) {
                String[] sameword = wordNet.getSameWord(word);
                result.append(Arrays.toString(sameword));
            }

        }


        return result.toString();
    }
}