package devtest;

import devtest.Exceptions.FileWriterException;
import devtest.entities.Suggestion;
import devtest.entities.SuggestionConverter;
import devtest.goeuro.SuggestionApi;
import devtest.goeuro.dto.SuggestDto;

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

    List<SuggestDto> dtos = suggestionApi.getSuggestionByName(locationQueryName);

    if (dtos.isEmpty()) {
      return;
    }

    try {
      for (SuggestDto dto : dtos) {
        Suggestion suggestion = SuggestionConverter.toEntity(dto);
        writer.write(suggestion);
      }
    } catch (FileWriterException e) {
      System.err.println("cannot write suggestions to CSV file: " + e.getMessage());
      return;
    }
  }

}
