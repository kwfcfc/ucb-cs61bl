package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import wordnet.WordGraph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

        Iterator<String> queries = words.iterator();

        Set<String> response = new HashSet<>();
        if (queries.hasNext()) {
            response.addAll(wordGraph.searchHyponyms(queries.next()));
        }

        while (queries.hasNext()) {
            response.retainAll(wordGraph.searchHyponyms(queries.next()));
        }

        return response.toString();
    }
}
