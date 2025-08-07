package main;

import java.util.List;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;

public class HistoryTextHandler extends NgordnetQueryHandler {
    NGramMap ngm;

    public HistoryTextHandler(NGramMap map) {
        this.ngm = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        StringBuilder responseBuilder = new StringBuilder();
        /*
         * StringBuilder responseBuilder = new
         * StringBuilder("You entered the following info into the browser:\n");
         * responseBuilder.append("Words: ").append(q.words()).append("\n");
         * responseBuilder.append("Start Year: ").append(q.startYear()).append("\n");
         * responseBuilder.append("End Year: ").append(q.endYear()).append("\n");
         */
        for (String word : words) {
            responseBuilder.append(word + ": ").append(ngm.weightHistory(word, startYear, endYear)).append("\n");
        }
        return responseBuilder.toString();
    }
}
