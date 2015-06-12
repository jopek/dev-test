package devtest;

import devtest.Exceptions.FileWriterException;
import devtest.entities.Suggestion;
import devtest.entities.SuggestionConverter;
import devtest.goeuro.SuggestionApi;
import devtest.goeuro.dto.SuggestDto;

import java.io.IOException;
import java.util.List;

/**
 * Date: 09.06.15
 */
public class LocationToCsv {

  private final SuggestionApi suggestionApi;

  private final CsvFileWriter writer;

  public LocationToCsv(CsvFileWriter writer, SuggestionApi suggestionApi) {
    this.suggestionApi = suggestionApi;
    this.writer = writer;
  }

  public void execute(String locationQueryName) {
    if (locationQueryName.isEmpty()) {
      return;
    }

    List<SuggestDto> dtos = null;
    try {
      dtos = suggestionApi.getSuggestionByName(locationQueryName);
    } catch (IOException e) {
      System.out.println("could not fetch suggestions: " + ((e.getCause() != null) ? e.getCause() : e.toString()));
      return;
    }

    if (dtos.isEmpty()) {
      System.out.println("empty list of suggestions");
      return;
    } else {
      System.out.println(String.format("found %d suggestions", dtos.size()));
    }

    try {
      for (SuggestDto dto : dtos) {
        Suggestion suggestion = SuggestionConverter.toEntity(dto);
        System.out.println(suggestion.toString());
        writer.write(suggestion);
      }
      writer.close();

    } catch (FileWriterException e) {
      System.out.println("cannot write suggestions to CSV file: " + e.getCause());
      return;
    }
  }

}
