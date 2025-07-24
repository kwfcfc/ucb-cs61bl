package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.HashMap;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    private HashMap<String, TimeSeries> wordTimeSeries;
    private TimeSeries yearCount;

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        In wordsIn = new In(wordsFilename);
        In countsIn = new In(countsFilename);

        yearCount = getYearCounts(countsIn);
        wordTimeSeries = getWordTimeSeries(wordsIn);
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        return new TimeSeries(wordTimeSeries.get(word), startYear, endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        return (TimeSeries) wordTimeSeries.get(word).clone();
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        return (TimeSeries) yearCount.clone();
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        return countHistory(word, startYear, endYear).dividedBy(yearCount);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        return countHistory(word).dividedBy(yearCount);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries result = new TimeSeries();

        for (String word : words) {
            result = result.plus(countHistory(word, startYear, endYear));
        }

        return result.dividedBy(yearCount);
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries result = new TimeSeries();

        for (String word : words) {
            result = result.plus(weightHistory(word));
        }

        return result;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.

    private static TimeSeries getYearCounts(In fileIn) {
        TimeSeries yearCounts = new TimeSeries();

        while (fileIn.hasNextLine()) {
            String[] line = fileIn.readLine().split(",");
            yearCounts.put(Integer.parseInt(line[0]), Double.parseDouble(line[1]));
        }

        return new TimeSeries(yearCounts, MIN_YEAR, MAX_YEAR); // return only year between min and max
    }

    private static HashMap<String, TimeSeries> getWordTimeSeries(In fileIn) {
        HashMap<String, TimeSeries> wordCounts = new HashMap<>();

        while (fileIn.hasNextLine()) {
            String[] line = fileIn.readLine().split("\t");
            String word = line[0];
            int year = Integer.parseInt(line[1]);
            if (year < MIN_YEAR || year > MAX_YEAR) {
                continue;
            }
            double count = Double.parseDouble(line[2]);
            if (!wordCounts.containsKey(word)) {
                wordCounts.put(word, new TimeSeries());
            }
            TimeSeries timeSeries = wordCounts.get(word);
            timeSeries.put(year, count);
        }

        return wordCounts;
    }
}
