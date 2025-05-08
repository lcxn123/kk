package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {

    private NGramMap nGramMap;

    public HistoryTextHandler(NGramMap map){
        nGramMap = map;
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        StringBuilder responseBuilder = new StringBuilder();

        for (String word : words) {
            TimeSeries wordTime = nGramMap.countHistory(word, startYear, endYear);
            if (wordTime == null) {
                responseBuilder.append(word)
                        .append(" this is null.")
                        .append("\n");
                continue;
            }
            String wordTimeString = wordTime.toString();
            responseBuilder.append(word)
                    .append(": {")
                    .append(wordTimeString)
                    .append("}")
                    .append("\n");
        }

        String response = responseBuilder.toString();
        return response;
    }
}
