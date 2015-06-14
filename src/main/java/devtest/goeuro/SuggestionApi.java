package devtest.goeuro;

import devtest.goeuro.dto.SuggestDto;
import org.apache.http.HttpException;

import java.io.IOException;
import java.util.List;

/**
 * Suggestion API endpoint Query Interface.
 */
public interface SuggestionApi {
  /**
   * retrieve list of suggested locations and airports.
   *
   * @param cityName get suggestions matching this initial string
   * @return list of suggested locations and airports ({@link SuggestDto})
   * @throws IOException
   * @throws HttpException
   */
  List<SuggestDto> getSuggestionByName(String cityName) throws IOException, HttpException;
}
