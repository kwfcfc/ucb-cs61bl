package main;

import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import plotting.Plotter;

public class HistoryHandler extends NgordnetQueryHandler {
  private NGramMap ngm;

  public HistoryHandler(NGramMap map) {
    ngm = map;
  }


  @Override
  public String handle(NgordnetQuery q) {
    List<String> words = q.words();
    int startYear = q.startYear();
    int endYear = q.endYear();

    List<TimeSeries> lts = new ArrayList<>();

    for (String word : words) {
      lts.add(ngm.weightHistory(word, startYear, endYear));
    }

    XYChart chart = Plotter.generateTimeSeriesChart(words, lts);

    return Plotter.encodeChartAsString(chart);
  }
}
