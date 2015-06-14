package devtest;

import devtest.Exceptions.FileWriterException;
import devtest.entities.Suggestion;
import devtest.entities.SuggestionConverter;
import devtest.goeuro.SuggestionApi;
import devtest.goeuro.dto.SuggestDto;
import org.apache.http.ConnectionClosedException;
import org.apache.http.HttpException;

import java.io.IOException;
import java.util.List;

/**
 * This class acts as the main "glue" between retrieving location suggestions and writing CSV files.
 * It handles all kinds of exceptions and passes data around
 */
public class LocationToCsv {

  public static final String COULD_NOT_FETCH = "could not fetch suggestions: ";

  private final SuggestionApi suggestionApi;

  private final CsvFileWriter writer;

  public LocationToCsv(CsvFileWriter writer, SuggestionApi suggestionApi) {
    this.suggestionApi = suggestionApi;
    this.writer = writer;
  }

  /**
   * run query on passed in suggestion api query object (any implementation of the {@link SuggestionApi} interface)
   *
   * @param locationQueryName preferably non empty location string
   */
  public void execute(String locationQueryName) {
    if (locationQueryName.isEmpty()) {
      return;
    }

    List<SuggestDto> dtos = null;
    try {
      dtos = suggestionApi.getSuggestionByName(locationQueryName);
    } catch (ConnectionClosedException e) {
      System.out.println(COULD_NOT_FETCH + "HTTP connection closed before content could be read");
      return;
    } catch (IOException e) {
      System.out.println(COULD_NOT_FETCH + ((e.getCause() != null) ? e.getCause().getMessage() : e.toString()));
      return;
    } catch (HttpException e) {
      System.out.println(COULD_NOT_FETCH + ((e.getCause() != null) ? e.getCause() : e.toString()));
      return;
    }

    if (dtos.isEmpty()) {
      System.out.println(String.format("no suggestions found for '%s'", locationQueryName));
      return;
    } else {
      System.out.println(String.format("found %d suggestions for '%s'", dtos.size(), locationQueryName));
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
