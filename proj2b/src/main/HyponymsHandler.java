package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import wordnet.WordGraph;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class HyponymsHandler extends NgordnetQueryHandler {
    private WordGraph wordGraph;
    private NGramMap ngram;

    public HyponymsHandler(WordGraph wordGraph, NGramMap ngram) {
        this.wordGraph = wordGraph;
        this.ngram = ngram;
    }

    @Override
    public String handle(NgordnetQuery q) {
        int k = q.k();

        if (k > 0) {
            return handleKN0(q);
        } else if (k == 0) {
            return handleK0(q);
        } else {
            return "";
        }
    }

    private String handleK0(NgordnetQuery q) {
        List<String> words = q.words();

        Set<String> response = new TreeSet<>();
        Iterator<String> queries = words.iterator();
        if (queries.hasNext()) {
            response.addAll(wordGraph.searchHyponyms(queries.next()));
        }

        while (queries.hasNext()) {
            response.retainAll(wordGraph.searchHyponyms(queries.next()));
        }

        return response.toString();
    }

    private String handleKN0(NgordnetQuery q) {
        int k = q.k();
        int startYear = q.startYear();
        int endYear = q.endYear();
        List<String> words = q.words();

        Comparator<String> popularityCompare = (s1, s2) -> {
            // use the reverse order to put larger one first
            return Integer.compare(wordCount(s2, startYear, endYear), wordCount(s1, startYear, endYear));
        };

        Set<String> topK = new TreeSet<String>(popularityCompare);
        Iterator<String> queries = words.iterator();

        if (queries.hasNext()) {
            topK.addAll(wordGraph.searchHyponyms(queries.next()));
        }       
        
        while (queries.hasNext()) {
            topK.retainAll(wordGraph.searchHyponyms(queries.next()));
        }        

        List<String> result = topK.stream().
                limit(k).
                filter(s -> wordCount(s, startYear, endYear) > 0).
                sorted().
                toList();

        return result.toString();
    }

    private int wordCount(String word, int startYear, int endYear) {
        TimeSeries wordSeries = ngram.countHistory(word, startYear, endYear);
        return wordSeries.data().stream().mapToInt(Double::intValue).sum();
    }
}
