package devtest;

import devtest.goeuro.SuggestionApi;

import java.io.FileWriter;

/**
 * Date: 09.06.15
 */
public class LocationToCsv {

  private final SuggestionApi suggestionApi;
  private final FileWriter writer;

  public LocationToCsv(SuggestionApi suggestionApi, FileWriter writer) {
    this.suggestionApi = suggestionApi;
    this.writer = writer;
  }
}
