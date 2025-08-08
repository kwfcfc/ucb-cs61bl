package main;

import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import wordnet.WordGraph;

public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymsHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {

        WordGraph wg = new WordGraph(hyponymFile, synsetFile);
        NGramMap ngm = new NGramMap(wordFile, countFile);
        return new HyponymsHandler(wg);
    }
}
