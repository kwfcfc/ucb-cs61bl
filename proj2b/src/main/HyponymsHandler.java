package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import wordnet.WordGraph;

import java.util.List;

public class HyponymsHandler extends NgordnetQueryHandler {
    private WordGraph wordGraph;

    public HyponymsHandler(WordGraph wordGraph) {
        this.wordGraph = wordGraph;
    }

    @Override
    public String handle(NgordnetQuery q) {
        int startYear = q.startYear();
        int endYear = q.endYear();
        List<String> words = q.words();

        StringBuilder response = new StringBuilder();
        for (String word : words) {
            response.append(wordGraph.searchHyponyms(word)).append('\n');
        }
        return response.toString();
    }
}
